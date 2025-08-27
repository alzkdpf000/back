// ===================== 공통 변수 =====================
let consultationPosts = [];
let page = 1;
let categoryList = [];
let keyword = '';

// ===================== 로딩 및 DOM 요소 =====================
const container = document.getElementById("intersectionObserver");
const pageContainer = document.getElementById("page-container");
const loading = document.getElementById("loading");

// ===================== 상담글 리스트 보여주기 =====================
const showList = (data) => {
    let html = "";

    if (!data.consultationPosts || data.consultationPosts.length === 0) {
        container.innerHTML = "<li>상담글이 없습니다.</li>";
        pageContainer.innerHTML = "";
        return;
    }

    data.consultationPosts.forEach(post => {
        html += `
            <li>
                <a href="/post/detail/${post.id}">
                    <div class="content-info">
                        <div class="content-info-text">
                            <div class="post-title">${post.consultationPostTitle}</div>
                            <div class="post-meta">
                                <span>작성자: ${post.memberName}</span>
                                <span>조회수: ${post.consultationPostViewCount}</span>
                            </div>
                        </div>
                    </div>
                </a>
            </li>
        `;
    });

    container.innerHTML = html;
};

// ===================== 페이징 =====================
const showPaging = (criteria) => {
    let html = "";

    if (criteria.hasPreviousPage) html += `<a href="#" data-page="${criteria.startPage - 1}">이전</a>`;
    for (let i = criteria.startPage; i <= criteria.endPage; i++) {
        html += `<a href="#" data-page="${i}">${i}</a>`;
    }
    if (criteria.hasNextPage) html += `<a href="#" data-page="${criteria.endPage + 1}">다음</a>`;

    pageContainer.innerHTML = html;

    pageContainer.querySelectorAll("a").forEach(link => {
        link.addEventListener("click", e => {
            e.preventDefault();
            page = Number(link.dataset.page);
            loadConsultationPosts(page, keyword, categoryList);
        });
    });
};

// ===================== 데이터 불러오기 =====================
const loadConsultationPosts = async (page = 1, kw = '', catList = []) => {
    keyword = kw;
    categoryList = catList;

    loading.style.display = "block";

    try {
        const data = await consultationPostListService.loadConsultationPosts(page, keyword, categoryList);
        showList(data);
        showPaging(data.criteria);
    } catch (err) {
        console.error("상담글 로드 실패:", err);
        container.innerHTML = "<li>상담글을 불러오는데 실패했습니다.</li>";
    } finally {
        loading.style.display = "none";
    }
};

// ===================== 카테고리 선택 이벤트 =====================
document.querySelectorAll("ul.category-list-wrap li button.category-btn").forEach(btn => {
    btn.addEventListener("click", () => {
        const text = btn.firstElementChild.textContent;
        if (btn.classList.contains("checked")) {
            btn.classList.remove("checked");
            categoryList.splice(categoryList.indexOf(text), 1);
        } else {
            btn.classList.add("checked");
            categoryList.push(text);
        }
    });
});

document.querySelector(".category-select-fix-btn").addEventListener("click", () => {
    page = 1;
    container.innerHTML = "";
    loadConsultationPosts(page, keyword, categoryList);
    document.querySelector(".category-modal").style.display = "none";
});

// ===================== 검색 =====================
document.getElementById("searchForm").addEventListener("submit", e => {
    e.preventDefault();
    page = 1;
    keyword = document.getElementById("keywordInput").value || '';
    container.innerHTML = "";
    loadConsultationPosts(page, keyword, categoryList);
});

// ===================== 초기 실행 =====================
document.addEventListener("DOMContentLoaded", () => {
    loadConsultationPosts(1);
});