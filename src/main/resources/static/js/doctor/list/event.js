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
    window.doctorLayout.loadDoctors(1, keyword, categoryList);
};

searchForm?.addEventListener("submit", e => {
    e.preventDefault();
    executeSearch();
});
searchBtn?.addEventListener("click", () => {
    executeSearch();
});

// ===================== 초기 로드 =====================
document.addEventListener("DOMContentLoaded", () => {
    window.doctorLayout.loadDoctors(1, '', []);
});