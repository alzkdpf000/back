let categoryList = [];

// ===================== 카테고리 모달 =====================
const categoryModal = document.querySelector(".category-modal");
const categoryModalOpenBtn = document.querySelector("button.category-container-modal-btn");
const categoryModalOpenBtnText = categoryModalOpenBtn.querySelector("span.check-list");
const categoryFinalSelect = document.querySelector(".category-select-fix-btn");
const categorySelectCancelAll = document.querySelector(".category-select-btn-del");
const categoryListBtns = document.querySelectorAll("ul.category-list-wrap li button.category-btn");

// 모달 열기
categoryModalOpenBtn?.addEventListener("click", () => {
    categoryModal.style.display = "flex";
});

// 카테고리 선택/취소
categoryListBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        const text = btn.textContent.trim();
        const idx = categoryList.indexOf(text);
        if (btn.classList.contains("checked")) {
            btn.classList.remove("checked");
            if (idx > -1) categoryList.splice(idx, 1);
        } else {
            btn.classList.add("checked");
            if (idx === -1) categoryList.push(text);
        }
    });
});

// 전체 취소
categorySelectCancelAll?.addEventListener("click", () => {
    categoryListBtns.forEach(btn => btn.classList.remove("checked"));
    categoryList = [];
});

// 최종 선택 완료 (검색 실행 X, 모달 닫기만)
categoryFinalSelect?.addEventListener("click", () => {
    if (categoryList.length === 0) categoryModalOpenBtnText.textContent = "전체";
    else if (categoryList.length === 1) categoryModalOpenBtnText.textContent = categoryList[0];
    else categoryModalOpenBtnText.textContent = `${categoryList[0]} 외 ${categoryList.length - 1}개`;

    if (categoryModal) categoryModal.style.display = "none";
});

// ===================== 검색 이벤트 =====================
const searchForm = document.getElementById("searchForm");
const searchBtn = document.querySelector(".total-search-input-wrap img[alt='search']");

let orderType = "latest"; // 초기 정렬: 최신순

const executeSearch = () => {
    const keyword = document.getElementById("keywordInput")?.value.trim() || '';
    window.consultationPostListLayout.loadConsultationPosts(1, keyword, categoryList, orderType);
};

searchForm?.addEventListener("submit", e => {
    e.preventDefault();
    executeSearch();
});
searchBtn?.addEventListener("click", () => {
    executeSearch();
});

// ===================== 정렬 버튼 =====================
const orderBtn = document.querySelector(".card-list-order-btn");
const orderText = orderBtn.querySelector(".change-order");

orderBtn.addEventListener("click", () => {
    // 토글
    if (orderType === "latest") {
        orderType = "viewCount";
        orderText.textContent = "조회 순";
    } else {
        orderType = "latest";
        orderText.textContent = "최신 순";
    }

    // 현재 키워드, 카테고리 가져오기
    const keyword = document.getElementById("keywordInput")?.value.trim() || '';
    const selectedCategories = [...document.querySelectorAll("ul.category-list-wrap li button.checked")]
        .map(btn => btn.textContent.trim());

    // 상담글 재조회
    executeSearch();
});

// ===================== 초기 로드 =====================
document.addEventListener("DOMContentLoaded", () => {
    window.consultationPostListLayout.loadConsultationPosts(1, '', [], orderType);
});