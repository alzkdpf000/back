const doctorLayout = (() => {

    const showList = (criteriaDTO) => {
        const container = document.getElementById("intersectionObserver");
        let html = "";

        criteriaDTO.doctorsList.forEach(doctor => {
            html += `
            <li>
                <a> 
                    <div class="content-info">
                        <img class="contentInfoImg img-doc-tag" src="https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp" width="48" height="48" alt="">
                        <div class="content-info-text">
                            <div class="depart-treatment-wrap">
                                <span class="first">진료 과목</span>
                                <span class="second">${doctor.doctorSpecialty}</span>
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