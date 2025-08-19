const btnFilterStatus = document.getElementById("btn-filter-status");
const allChecked = document.getElementById("allchecked1"); // 전체 선택 버튼
const allCanclechecked = document.getElementById("allflasechecked1"); //전체 취소 버튼
const checkBoxActive1 = document.getElementById("checkboxactive1"); // 답변 완료 버튼
const checkBoxActive2 = document.getElementById("checkboxactive2"); // 미답변 버튼
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


// 필터 팝업 토글
btnFilterStatus?.addEventListener("click", () => {
    popMenubtn.classList.toggle("show");
});


const inquiriesBody = document.getElementById("inquiriesBody");
// 모달 열기/닫기

inquiriesBody.addEventListener("click", async (e) => {
    const inquiryDetailBtn = e.target.closest("div#modal-open");
    console.log(e.target);
    if (inquiryDetailBtn) {
        const inquiryId = inquiryDetailBtn.dataset.inquiryid
        loading.style.display = "block";
        await inquiryService.getDetailInquiry(inquiryLayout.showDetailInquiry, inquiryId);
        setTimeout(() => {
            loading.style.display = "none";
            modal.classList.add("show");
            modal.style.display = "block";
        }, 1000)


    }
})
modalClose?.addEventListener("click", () => {
    modal.classList.remove("show");
    modal.style.display = "none";
});


const scrollBox = document.getElementById("bootpay-main")
let checkMore = true;
let inquires;
let inquiryScroll = false;
const showList = async (page = 1, keyword = "", categories = [], load = false) => {
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    inquiries = await inquiryService.getInquiries(inquiryLayout.showInquiries, page, keyword, categories, load);

    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
    checkMore = inquiries.inquiryMemberReplyDTOs.length === inquiries.scrollCriteria.rowCount;
    return inquiries;
}


scrollBox.addEventListener("scroll", async (e) => {
    if (!inquiryScroll) {
        return;
    }
    if (!checkMore) {
        return;
    }

    const scrollTop = scrollBox.scrollTop;
    // div의 보이는 높이
    const clientHeight = scrollBox.clientHeight;
    // div의 전체 콘텐츠 높이
    const scrollHeight = scrollBox.scrollHeight;

    if (scrollTop + clientHeight >= scrollHeight - 2) {
        console.log("바닥")
        console.log(inquiryScroll)
        console.log(inquires)
        //     바닥에 닿았을 때
        if (inquiryScroll && inquires) {
            inquiryScroll = false;
            console.log(inquires.search)
            inquires = await showList(inquires.search.page + 1, inquires.search.keyword, inquires.search.categories, true);
            console.log("스크롤 이벤트야");
            checkMore = inquires.inquiryMemberReplyDTOs.length === inquires.scrollCriteria.rowCount;
        }

        setTimeout(() => {
            if (inquires !== null && checkMore) {
                inquiryScroll = true
            }
        }, 2000);
    }
})


// const allChecked = document.getElementById("allchecked1"); // 전체 선택 버튼
// const allCanclechecked = document.getElementById("allflasechecked1"); //전체 취소 버튼
// const checkBoxActive1 = document.getElementById("checkboxactive1"); // 답변 완료 버튼
// const checkBoxActive2 = document.getElementById("checkboxactive2"); // 미답변 버튼
let answerStatusSet = new Set();
const inquiryKeywordInput = document.getElementById("inquiryKeyword");
const inquiryKeywordBtn = document.getElementById("inquiryKeywordBtn");

inquiryKeywordInput.addEventListener("keydown", async (e) => {
    if (e.key === "Enter") {
        document.getElementById("inquiriesBody").innerHTML = "";
        inquires = await showList(1, inquiryKeywordInput.value.trim(), inquires.search.categories, false);
    }
})
inquiryKeywordBtn.addEventListener("click", async (e) => {
    document.getElementById("inquiriesBody").innerHTML = "";
    inquires = await showList(1, inquiryKeywordInput.value.trim(), inquires.search.categories, false);

})


let actionCheck = true;


const actionCheckBox = async (page, keyword, categories = []) => {
    actionCheck = false;
    inquiryScroll= false;
    document.getElementById("inquiriesBody").innerHTML = "";
    inquires = await showList(1, keyword, categories);
    setTimeout(() => {
        actionCheck = true;
        inquiryScroll = true;
    }, 1000);
    return inquires;
}
// 답변 있는 체크박스 토글
checkBoxActive1?.addEventListener("click", async (e) => {
    if (!actionCheck) {
        return;
    }
    checkBoxActive1.classList.toggle("active");

    if(answerStatusSet.has(checkBoxActive1.dataset.count)){
        answerStatusSet.delete(checkBoxActive1.dataset.count);
    }else{
        answerStatusSet.add(checkBoxActive1.dataset.count);
    }

    console.log("답변완료 입니다." + categories);
    let checkStatus = answerStatusSet.size === 2 ? [] : Array.from(answerStatusSet);
    inquires = await actionCheckBox(1, inquires.search.keyword, checkStatus);

});
// 답변 없음 체크 박스 토글
checkBoxActive2?.addEventListener("click", async (e) => {
    if (!actionCheck) {
        return;
    }
    checkBoxActive2.classList.toggle("active");

    if(answerStatusSet.has(checkBoxActive2.dataset.count)){
        answerStatusSet.delete(checkBoxActive2.dataset.count);
    }else{
        answerStatusSet.add(checkBoxActive2.dataset.count);
    }
    console.log("미답변 입니다." + categories);
    let checkStatus = answerStatusSet.size === 2 ? [] : Array.from(answerStatusSet);
    inquires = await actionCheckBox(1, inquires.keyword, checkStatus);
});

// 전체 선택 / 해제
allChecked?.addEventListener("click", async (e) => {
    if (!actionCheck) {
        return;
    }
    answerStatusSet.add("0");
    answerStatusSet.add("1");
    checkBoxActive1?.classList.add("active");
    checkBoxActive2?.classList.add("active");
    inquires = await actionCheckBox(1, inquires.search.keyword, []);

});
// 전체 해제
allCanclechecked?.addEventListener("click", async (e) => {
    if (!actionCheck) {
        return;
    }
    answerStatusSet.clear();
    checkBoxActive1?.classList.remove("active");
    checkBoxActive2?.classList.remove("active");
    inquires = await actionCheckBox(1, inquires.search.keyword, []);
});
