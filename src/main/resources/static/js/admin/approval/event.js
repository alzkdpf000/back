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
        await showPendingDoctors(e.target.dataset.page);

    }
})

const approvalTbody = document.getElementById("approvalTbody");

approvalTbody.addEventListener("click",async (e)=>{
    const pendingBtn = e.target.closest("button.action-btn");
    if (pendingBtn) {
        const doctorId = pendingBtn.dataset.doctorid;
        loading.style.display = "block";
        await approvalService.getPendingDoctor(approvalLayout.showPendingDoctor,doctorId);
        setTimeout(() => {
            loading.style.display = "none";
            approvalModal.classList.add("show")
            approvalModal.style.display = "block"
        }, 1000)
    }
})





// // 승인 여부
const acceptBtn = document.getElementById("accept");
const refuseBtn = document.getElementById("refuse");
//
// // 승인 버튼
// acceptBtn.forEach((btn) => {
//     btn.addEventListener("click", (e) => {
//         const tr = e.currentTarget.closest("tr");
//         const status = tr.querySelector(".approval-status");
//         status.textContent = "승인 완료";
//         status.style.color = "green";
//     });
// });
//
// // 거절 버튼
// refuseBtn.forEach((btn) => {
//     btn.addEventListener("click", (e) => {
//         console.log(222);
//         const tr = e.currentTarget.closest("tr");
//         const status = tr.querySelector(".approval-status");
//         status.textContent = "승인 거절";
//         status.style.color = "red";
//     });
// });
