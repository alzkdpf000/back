// 일반회원 상세 모달 창 열고 닫는 이벤트
const approvalModal = document.querySelector(".approval-modal");
const closeApprovalButton = document.getElementById("approvalModalClose");


closeApprovalButton.addEventListener("click", (e) => {
    approvalModal.classList.remove("show");
    document.body.classList.remove("modal-open");

    setTimeout(() => {
        approvalModal.style.display = "none";
    }, 100);
});

let approvalPage = 1;
const showPendingDoctors = async (page = 1) => {
    loading.style.display = "block";
    await approvalService.getPendingDoctors(approvalLayout.showPendingDoctors, page)
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
}
const paginationApproval = document.querySelector(".pagination.bootpay-pagination.approval-pagination");
paginationApproval.addEventListener("click", async (e) => {
    if (e.target.classList.contains("paging")) {
        e.preventDefault();
        approvalPage = e.target.dataset.page
        await showPendingDoctors(approvalPage);

    }
})

const approvalTbody = document.getElementById("approvalTbody");

approvalTbody.addEventListener("click", async (e) => {
    const pendingBtn = e.target.closest("button.action-btn");
    if (pendingBtn) {
        const doctorId = pendingBtn.dataset.doctorid;
        loading.style.display = "block";
        await approvalService.getPendingDoctor(approvalLayout.showPendingDoctor, doctorId);
        setTimeout(() => {
            loading.style.display = "none";
            approvalModal.classList.add("show")
            approvalModal.style.display = "block"
        }, 1000)
    }
})

const showWarnModal = (modalMessage) => {
    document.getElementById("content-wrap").innerHTML = modalMessage;
    document.querySelector("div.warn-modal-approval-check").style.animation = "popUp 0.5s";
    document.querySelector("div.modal-approval-check").style.display = "flex";
    document.querySelector("div.modal-footer-approval").style.display = "flex";
};


// // 승인 여부
const acceptBtn = document.getElementById("accept");
const refuseBtn = document.getElementById("refuse");
const yesBtn = document.getElementById("yes");
const noBtn = document.getElementById("no");

let yesAction = null;


// // 승인 버튼
acceptBtn.addEventListener("click", (e) => {
    showWarnModal("승인하시겠습니까?");
    yesAction = async () => {
        return await approvalService.approve(document.getElementById("pendingId").textContent);
    };
})
refuseBtn.addEventListener("click", (e) => {

    showWarnModal("거절하시겠습니까?");
    yesAction = async () => {
        return await approvalService.reject(document.getElementById("pendingId").textContent);
    };
})

yesBtn.addEventListener("click", async () => {
    if (yesAction) {
        let response = await yesAction();
        console.log(response);
        document.querySelector("div.modal-footer-approval").style.display = "none";
        document.getElementById("content-wrap").innerHTML = response.msg;
        setTimeout(async () => {
            if (response.status === 200) {
                await showPendingDoctors(approvalPage);
                document.body.classList.remove("modal-open");
                setTimeout(() => {
                    approvalModal.style.display = "none";
                }, 1000);
            }
            document.querySelector("div.warn-modal-approval-check").style.animation =
                "popDown 0.5s";
            document.querySelector("div.modal-approval-check").style.display = "none";
        }, 2000);


    }
});


noBtn.addEventListener("click", (e) => {
    document.querySelector("div.warn-modal-approval-check").style.animation =
        "popDown 0.5s";
    document.querySelector("div.modal-approval-check").style.display = "none";
})
