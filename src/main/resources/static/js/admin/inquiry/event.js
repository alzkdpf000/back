const btnFilterStatus = document.getElementById("btn-filter-status");
const allChecked = document.getElementById("allchecked1");
const allCanclechecked = document.getElementById("allflasechecked1");
const checkBoxActive1 = document.getElementById("checkboxactive1");
const checkBoxActive2 = document.getElementById("checkboxactive2");
const popMenubtn = document.getElementById("pop-menu-bt2");
const modalClose = document.getElementById("close");
const modal = document.getElementById("modal");
const modalOpen = document.getElementById("modal-open");
const userMenuBtn = document.getElementById("usermenubtn");
const userMenu = document.getElementById("usermenu");

// 유저 메뉴 버튼
userMenuBtn?.addEventListener("click", (e) => {
    userMenu.classList.toggle("show");
});

// 답변 있는 체크박스 토글
checkBoxActive1?.addEventListener("click", () => {
    checkBoxActive1.classList.toggle("active");
});
// 답변 없음 체크 박스 토글
checkBoxActive2?.addEventListener("click", () => {
    checkBoxActive2.classList.toggle("active");
});

// 전체 선택 / 해제
allChecked?.addEventListener("click", () => {
    checkBoxActive1?.classList.add("active");
    checkBoxActive2?.classList.add("active");
});
// 전체 해제
allCanclechecked?.addEventListener("click", () => {
    checkBoxActive1?.classList.remove("active");
    checkBoxActive2?.classList.remove("active");
});

// 필터 팝업 토글
btnFilterStatus?.addEventListener("click", () => {
    popMenubtn.classList.toggle("show");
});


const inquiriesBody = document.getElementById("inquiriesBody");
// 모달 열기/닫기

inquiriesBody.addEventListener("click", (e) => {
    console.log(e.target);
    if (e.target.closest("div#modal-open")) {
        modal.classList.add("show");
        modal.style.display = "block";

    }
})
modalClose?.addEventListener("click", () => {
    modal.classList.remove("show");
    modal.style.display = "none";
});

const scrollBox = document.getElementById("bootpay-main")
let page =1;
let query= "";
let answerStatus = "all";
scrollBox.addEventListener("scroll", async (e) => {
    console.log(checkMore);
    console.log(inquiryScroll);
    if (!checkMore) {
        return;
    }

    const scrollTop = scrollBox.scrollTop;
    // div의 보이는 높이
    const clientHeight = scrollBox.clientHeight;
    // div의 전체 콘텐츠 높이
    const scrollHeight = scrollBox.scrollHeight;

    if (scrollTop + clientHeight >= scrollHeight - 2) {
        //     바닥에 닿았을 때
        if (inquiryScroll) {
            inquires = await showList(++page,query,answerStatus,true);
            inquiryScroll = false;
        }
        checkMore = inquires.inquiryMemberReplyDTOs.length === inquires.scrollCriteria.rowCount;
        setTimeout(() => {
            if (inquires !== null && checkMore) {
                inquiryScroll = true
            }
        }, 1100);
    }
})



