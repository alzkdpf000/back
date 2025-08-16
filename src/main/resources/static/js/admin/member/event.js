const doctorMenuBtn = document.getElementById("doctorMenu");
const memberMenuBtn = document.getElementById("memberMenu");
let memberQuery = "";
// 페이지 번호 클릭 이벤트(데이터를 받아와야 하는 곳이라 주석 처리)
const paginationMember = document.querySelector(".pagination.bootpay-pagination.member-pagination");

const showMembers = async (page = 1, load = false, query = "") => {
    await memberService.getMembers(memberLayout.showMembers, page, load, query)
}
paginationMember.addEventListener("click", async (e) => {
    if (e.target.classList.contains("paging")) {
        e.preventDefault();
        await showMembers(e.target.dataset.page, true, memberQuery);

    }
})


doctorMenuBtn.addEventListener("click", (e) => {
    e.preventDefault();
    doctorMenuBtn.classList.add("checked");
    memberMenuBtn.classList.remove("checked");
})

memberMenuBtn.addEventListener("click", async (e) => {

    document.querySelectorAll("div.wide-page").forEach((divTag) => {
        divTag.style.display = "none";
    })
    document.getElementById("members").style.display = "block";
    e.preventDefault();
    memberMenuBtn.classList.add("checked");
    loading.style.display = "block";
    await showMembers();
    setTimeout(async () => {
        loading.style.display = "none";
    }, 100)
    doctorMenuBtn.classList.remove("checked");
})


const memberMoadl = document.getElementById("memberModal");
const membersTbody = document.getElementById("membersTbody");
membersTbody.addEventListener("click", async (e) => {
    const memberDetailBtn = e.target.closest(".member-detail-btn");
    if (memberDetailBtn) {
        const memberId = memberDetailBtn.dataset.memberid;
        loading.style.display = "block";
        await memberService.getDetailMember(memberLayout.showMemberDetail, memberId);
        setTimeout(() => {
            loading.style.display = "none";
            memberMoadl.classList.add("show");
            memberMoadl.style.display = "block";
        }, 1000)
    }
});


// 일반회원 상세 모달 창 열고 닫는 이벤트
const memberModal = document.querySelector(".member-modal");
const actionButtons = document.querySelectorAll(".action-btn");
const closeButtons = document.querySelectorAll(".close");
const closeFooterButton = document.querySelector(".btn-close");

actionButtons.forEach((actionButton) => {
    actionButton.addEventListener("click", (e) => {
        console.log(222);
        memberModal.style.display = "block";

        // 일정 시간이 지난 후 한번만 실행
        setTimeout(() => {
            memberModal.classList.add("show");
            memberModal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);
    });
});

closeButtons.forEach((closeButton) => {
    closeButton.addEventListener("click", (e) => {
        memberModal.classList.remove("show");
        document.body.classList.remove("modal-open");

        setTimeout(() => {
            memberModal.style.display = "none";
        }, 100);
    });
});

memberModal.addEventListener("click", (e) => {
    if (e.target === modal) {
        memberModal.classList.remove("show");
        document.body.classList.remove("modal-open");

        setTimeout(() => {
            memberModal.style.display = "none";
        }, 100);
    }
});

closeFooterButton.addEventListener("click", (e) => {
    memberModal.classList.remove("show");
    document.body.classList.remove("modal-open");

    setTimeout(() => {
        memberModal.style.display = "none";
    }, 100);
});

