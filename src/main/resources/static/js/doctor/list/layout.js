const doctorLayout = (() => {

    const showList = (criteriaDTO) => {
        const container = document.getElementById("intersectionObserver");
        let html = "";

        criteriaDTO.doctorsList.forEach(doctor => {
            html += `
        <li>
            <a>
                <div class="content-info">
                    <img class="contentInfoImg img-doc-tag" src="https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp" width="48" height="48" alt="" style="color: transparent">
                    <div class="content-info-text">
                        <div class="type-user-wrapper">
                            <div class="depart-treatment">
                                <div class="doctor-info">
                                    <span class="doctorinfo-name">
                                        ${doctor.memberName}
                                    </span>
                                    <div class="docterinfo-favorite-wrapper">
                                        <img alt="관심토픽 이미지" loading="lazy" width="15" height="15" decoding="async" src="/images/heart-empty.png" style="color: transparent;">
                                        <span class="doctorinfo-favoriteCount">${doctor.likes?.length || 0}</span><span class="unit">명</span>
                                    </div>
                                </div>
                                <div class="depart-treatment-wrap">
                                    <span class="first">진료 과목</span>
                                    <span class="second">${doctor.doctorSpecialty}</span>
                                </div>
                            </div>
                        </div>
                        <div class="info-container hospital-info">
                            <span class="first">소속 병원</span>
                            <span class="second">${doctor.hospitalName}</span>
                            <span class="first detail-address">상세 주소</span>
                            <span class="second">${doctor.hospitalAddress}</span>
                        </div>
                        <div class="info-container">
                            <span class="first">병원 전화 번호</span>
                            <span class="second">${doctor.hospitalPhone}</span>
                        </div>
                        <div class="icon-btn-wrap">
                            <div class="icon-container">
                                <img src="/images/DrIcon-Photoroom.png" width="35" height="35" alt="">
                                <span class="greeting"><strong>최선</strong>을 다하겠습니다.</span>
                            </div>
                            <div class="btn-container">
                                <div class="btn-wrap">
                                    <button class="btnshape interest-btn">
                                        <span>관심 추가</span>
                                        <img src="/images/plus.png">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </li>
        `;
        });

        container.innerHTML = html;
    };

    const showPaging = (criteria) => {
        const pageContainer = document.getElementById("page-container");
        let html = "";

        if(criteria.hasPreviousPage) html += `<a href="#" data-page="${criteria.startPage - 1}">이전</a>`;
        for(let i=criteria.startPage; i<=criteria.endPage; i++){
            html += `<a href="#" data-page="${i}">${i}</a>`;
        }
        if(criteria.hasNextPage) html += `<a href="#" data-page="${criteria.endPage + 1}">다음</a>`;

        pageContainer.innerHTML = html;

        // 클릭 이벤트
        pageContainer.querySelectorAll("a").forEach(link => {
            link.addEventListener("click", e => {
                e.preventDefault();
                const page = link.dataset.page;
                loadDoctors(page);
            });
        });
    };

    const loadDoctors = async (page=1) => {
        const criteriaDTO = await doctorService.getDoctors(page);
        if(criteriaDTO){
            showList(criteriaDTO);
            showPaging(criteriaDTO.criteria);
        }
    };

    return { loadDoctors };
})();

document.addEventListener("DOMContentLoaded", () => {
    doctorLayout.loadDoctors(1);
});