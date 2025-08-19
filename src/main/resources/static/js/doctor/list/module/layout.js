const doctorLayout = ((currentMemberId = 1) => {

    // 의사 목록 표시
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
                <a>
                    <div class="content-info">
                        <img class="contentInfoImg img-doc-tag" 
                             src="https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp" 
                             width="48" height="48" alt="">
                        <div class="content-info-text">
                            <div class="doctor-info">
                                <span class="doctorinfo-name">${doctor.memberName}</span>
                                <div class="docterinfo-favorite-wrapper">
                                    <img class="like-btn" 
                                         data-doctor-id="${doctor.id}" 
                                         src="${doctor.liked ? '/images/heart-filled.png' : '/images/heart-empty.png'}" 
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

        console.log("memberId:", currentMemberId);
        container.innerHTML = html;

        // 좋아요 버튼 이벤트
        container.querySelectorAll(".like-btn").forEach(btn => {
            btn.addEventListener("click", async () => {
                const doctorId = Number(btn.dataset.doctorId);
                try {
                    const result = await doctorService.toggleLike(doctorId, currentMemberId);
                    btn.src = result === "liked" ? '/images/heart-filled.png' : '/images/heart-empty.png';
                    const count = await doctorService.getLikesCount(doctorId);
                    btn.nextElementSibling.textContent = count;
                } catch(err) {
                    console.error("좋아요 토글 실패:", err);
                }
            });
        });
    };

    // 페이징
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
                loadDoctors(link.dataset.page);
            });
        });
    };

    // 의사 목록 로드
    const loadDoctors = async (page = 1) => {
        try {
            const criteriaDTO = await doctorService.getDoctors(page, currentMemberId);
            showList(criteriaDTO);
            showPaging(criteriaDTO.criteria);
        } catch(err) {
            console.error("의사 목록 로드 실패:", err);
        }
    };

    return { loadDoctors };
})(1);

document.addEventListener("DOMContentLoaded", () => {
    doctorLayout.loadDoctors(1);
});