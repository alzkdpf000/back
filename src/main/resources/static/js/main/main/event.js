const mainCategories = document.querySelectorAll(".coach-mark-container li span");
let activeSpan = document.querySelector(".btn-content.active");




mainCategories.forEach((span) => {
    span.addEventListener("click", () => {
 
        activeSpan.classList.remove("active");


        span.classList.add("active");
        activeSpan = span;
    });
});

const categoryListBtns = document.querySelectorAll(
    "ul.category-list-wrap li button.category-btn"
);

const categoryList = [];
categoryListBtns.forEach((categoryListBtn) => {
    categoryListBtn.addEventListener("click", (e) => {
        let text = categoryListBtn.firstElementChild.textContent;
        if (categoryListBtn.classList[1]) {
            categoryListBtn.classList.remove("checked");
            // 해당 부분에서 배열 원소 값 삭제
            categoryList.splice(categoryList.indexOf(text));
        } else {
            categoryListBtn.classList.add("checked");
            // 해당 부분에서 배열 원소 추가 해주기
            categoryList.push(text);
        }
    });
});

// 선택한 카테고리 모두 취소
// 선택 취소 버튼
const categorySelectCancleAll = document.querySelector(
    ".category-select-btn-del"
);
categorySelectCancleAll.addEventListener("click", (e) => {
    categoryListBtns.forEach((categoryListBtn) => {
        let text = null;
        if (categoryListBtn.classList[1]) {
            text = categoryListBtn.firstElementChild.textContent;
            categoryListBtn.classList.remove("checked");
            // 해당 부분에서 배열 원소 값 삭제
            categoryList.splice(categoryList.indexOf(text));
        }
    });
});

// 선택한 카테고리들로 보여주도록 하고 모달 창 닫기
// 아무것도 선택 안하면 전체보여주기
// 선택 완료 버튼
const categoryFinalSelect = document.querySelector(".category-select-fix-btn");
const categoryModal = document.querySelector(".category-modal");
const categoryModalOpenBtnText = document.querySelector(
    "button.category-container-modal-btn span.check-list"
);
categoryFinalSelect.addEventListener("click", (e) => {
    // console.log(categoryList == false);
    if (categoryList.length === 0) {
        console.log(11);
        categoryModalOpenBtnText.textContent = "전체";
    } else {
        console.log(categoryList);
        categoryModalOpenBtnText.textContent =
            categoryList.length > 1
                ? `${categoryList[0]} 외 ${categoryList.length - 1}개`
                : `${categoryList[0]}`;
        console.log(categoryModalOpenBtnText.textContent);
    }

    categoryModal.style.display = "none";
});

// 카테고리 모달 넣기
const categoryModalOpenBtn = document.querySelector(
    "button.category-container-modal-btn"
);
categoryModalOpenBtn.addEventListener("click", (e) => {
    console.log(111);
    categoryModal.style.display = "flex";
});

let clickable = true;
const cardListOrderBtn = document.querySelector(".card-list-order-btn");
cardListOrderBtn.addEventListener("click", (e) => {
    if (!clickable) return; // 클릭 무시
    clickable = false;
    const spanTag = document.querySelector(".card-list-btn-text");
    if (spanTag.classList[1]) {
        console.log(1);
        spanTag.classList.remove("change-order");
        spanTag.textContent = "답변 순";
    } else {
        console.log(2);
        spanTag.classList.add("change-order");
        spanTag.textContent = "최신 순";
    }
    setTimeout(() => {
        clickable = true;
    }, 500);
});