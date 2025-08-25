// ===================== 공통 설정 =====================
HTMLCollection.prototype.forEach = Array.prototype.forEach;
let modalCheck;

// ===================== 모달 관련 함수 =====================
const showWarnModal = (modalMessage) => {
    modalCheck = false;
    document.getElementById("content-wrap").innerHTML = modalMessage;
    document.querySelector("div.warn-modal").style.animation = "popUp 0.5s";
    document.querySelector("div.modal").style.display = "flex";
    setTimeout(() => { modalCheck = true; }, 500);
};

document.querySelector("div.modal").addEventListener("click", () => {
    if (!modalCheck) return;
    document.querySelector("div.warn-modal").style.animation = "popDown 0.5s";
    setTimeout(() => { document.querySelector("div.modal").style.display = "none"; }, 450);
});

// ===================== 카테고리 모달 =====================
const categoryModal = document.querySelector(".category-modal");
const categoryModalOpenBtn = document.querySelector("button.category-container-modal-btn");
const categoryModalOpenBtnText = categoryModalOpenBtn.querySelector("span.check-list");
const categoryFinalSelect = document.querySelector(".category-select-fix-btn");
const categorySelectCancelAll = document.querySelector(".category-select-btn-del");
const categoryListBtns = document.querySelectorAll("ul.category-list-wrap li button.category-btn");

let categoryList = [];

categoryModalOpenBtn.addEventListener("click", () => {
    categoryModal.style.display = "flex";
});

categoryListBtns.forEach((btn) => {
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

categorySelectCancelAll.addEventListener("click", () => {
    categoryListBtns.forEach((btn) => {
        if (btn.classList.contains("checked")) {
            const text = btn.firstElementChild.textContent;
            btn.classList.remove("checked");
            categoryList.splice(categoryList.indexOf(text), 1);
        }
    });
});

categoryFinalSelect.addEventListener("click", () => {
    if (categoryList.length === 0) {
        categoryModalOpenBtnText.textContent = "전체";
    } else if (categoryList.length === 1) {
        categoryModalOpenBtnText.textContent = categoryList[0];
    } else {
        categoryModalOpenBtnText.textContent = `${categoryList[0]} 외 ${categoryList.length - 1}개`;
    }
    categoryModal.style.display = "none";

    // 카테고리 선택 완료 후 검색 실행
    const keyword = document.getElementById("keywordInput").value || '';
    doctorLayout.loadDoctors(1, keyword, categoryList);
});

// ===================== 의사 목록 JS =====================
const doctorLayout = ((currentMemberId = 31) => {

    const showList = (criteriaDTO) => {
        const container = document.getElementById("intersectionObserver");
        let html = "";

        if (!criteriaDTO.doctorsList || criteriaDTO.doctorsList.length === 0) {
            container.innerHTML = "<li>의사 정보가 없습니다.</li>";
            return;
        }

        criteriaDTO.doctorsList.forEach(doctor => {
            html += `
                <li>
                  <a href="/doctor/detail/${doctor.id}?page=1">
                    <div class="content-info">
                        <img class="contentInfoImg img-doc-tag" 
                             src="${doctor.memberKakaoProfileUrl || 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp'}" 
                             width="48" height="48" alt="">
                        <div class="content-info-text">
                            <div class="doctor-info">
                                <span class="doctorinfo-name">${doctor.memberName}</span>
                                <div class="docterinfo-favorite-wrapper">
                                    <img class="like-btn" 
                                         data-doctor-id="${doctor.id}" 
                                         src="${doctor.liked ? '/images/heart.png' : '/images/heart-empty.png'}" 
                                         style="cursor:pointer; width:15px; height:15px;">
                                    <span class="doctorinfo-favoriteCount">${doctor.likesCount || 0}</span><span class="unit">명</span>
                                </div>
                            </div>
                            <div class="depart-treatment-wrap">
                                <span class="first">진료 과목</span>
                                <span class="second">${doctor.doctorSpecialty}</span>
                            </div>
                            <div class="info-container hospital-info">
                                <span class="first">소속 병원</span>
                                <span class="second">${doctor.hospitalName || ""}</span>
                                <span class="first detail-address">상세 주소</span>
                                <span class="second">${doctor.hospitalAddress || ""}</span>
                            </div>
                            <div class="info-container">
                                <span class="first">병원 전화 번호</span>
                                <span class="second">${doctor.hospitalPhone || ""}</span>
                            </div>
                        </div>
                    </div>
                </a>
            </li>
            `;
        });

        container.innerHTML = html;

        container.querySelectorAll(".like-btn").forEach(btn => {
            btn.addEventListener("click", async (e) => {
                e.preventDefault();
                e.stopPropagation();

                const doctorId = Number(btn.dataset.doctorId);
                const wrapper = btn.closest(".docterinfo-favorite-wrapper");
                const countSpan = wrapper.querySelector(".doctorinfo-favoriteCount");

                try {
                    const result = await doctorService.toggleLike(doctorId, currentMemberId);
                    // 서버에서 반환되는 상태
                    if (result === "liked") {
                        btn.src = '/images/heart.png'; // 채워진 하트
                        countSpan.textContent = Number(countSpan.textContent) + 1;
                    } else if (result === "unliked") {
                        btn.src = '/images/heart-empty.png'; // 빈 하트
                        countSpan.textContent = Number(countSpan.textContent) - 1;
                    }

                } catch(err) {
                    console.error("좋아요 토글 실패:", err);
                    showWarnModal("로그인 후 이용해주세요.");
                }
            });
        });
    };

    const showPaging = (criteria) => {
        const pageContainer = document.getElementById("page-container");
        let html = "";

        if(criteria.hasPreviousPage) html += `<a href="#" data-page="${criteria.startPage - 1}">이전</a>`;
        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            html += `<a href="#" data-page="${i}">${i}</a>`;
        }
        if(criteria.hasNextPage) html += `<a href="#" data-page="${criteria.endPage + 1}">다음</a>`;

        pageContainer.innerHTML = html;

        pageContainer.querySelectorAll("a").forEach(link => {
            link.addEventListener("click", e => {
                e.preventDefault();
                const page = Number(link.dataset.page);
                const keyword = document.getElementById("keywordInput").value || '';
                loadDoctors(page, keyword, categoryList);
            });
        });
    };

    const loadDoctors = async (page = 1, keyword = '', selectedCategories = []) => {
        try {
            const params = new URLSearchParams();
            params.append('keyword', keyword);
            selectedCategories.forEach(cat => params.append('categories', cat));

            const res = await fetch(`/api/doctors/list/${page}?${params.toString()}`);
            if (!res.ok) throw new Error("의사 목록 로드 실패");

            const data = await res.json();
            showList(data);
            showPaging(data.criteria);
        } catch (err) {
            console.error(err);
        }
    };

    document.getElementById("searchForm").addEventListener("submit", e => {
        e.preventDefault();
        const keyword = document.getElementById("keywordInput").value || '';
        loadDoctors(1, keyword, categoryList);
    });

    return { loadDoctors: loadDoctors };
})(31);

// ===================== 초기 실행 =====================
document.addEventListener("DOMContentLoaded", () => {
    doctorLayout.loadDoctors(1);
});