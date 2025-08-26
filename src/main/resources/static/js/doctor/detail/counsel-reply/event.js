document.addEventListener("DOMContentLoaded", async () => {

    const pathParts = window.location.pathname.split("/");
    const doctorId = pathParts[pathParts.length - 1];

    const urlParams = new URLSearchParams(window.location.search);
    const page = urlParams.get("page") || 1;

    try {
        // DTO 받아서 layout.js에 넘김
        const detailDTO = await doctorService.getDoctorDetail(doctorId, page);
        doctorLayout.loadDoctorDetailFromDTO(detailDTO);
    } catch (e) {
        console.error("의사 상세 정보 로딩 실패:", e);
    }
});

const reviewRegBtn = (className, formTag, tagClass) => {
    const btnTag = document.querySelector(`${className}`);
    let btnCheck = true;
    btnTag.addEventListener("click", (e) => {
        console.log(btnTag);
        console.log(formTag);
        console.log(btnCheck);
        const divTag = document.querySelector(`${tagClass}`);

        if (btnCheck) {
            divTag.classList.add("active");
            formTag[
                "reviewContent"
                ].placeholder = `[나중에 유저 이름] 후기를 입력해주세요.`;
            for (let i = 0; i < 5; i++) {
                svgStars[i].style.fill = "#E0E0E0";
            }
        } else {
            divTag.classList.remove("active");
            document.getElementById("rate").value = 0;
        }
        btnCheck = !btnCheck;
    });
};
// 검색 결과 없을 때
reviewRegBtn(".noexist-search-btn", reviewForm_v1, ".v1");
// 검색 결과 있을 때
reviewRegBtn(".exist-review-btn", reviewForm_v2, ".v2");

// 별점 클릭 버튼들
const reviewStarBtns = document.querySelectorAll("li.star-list-content");
// 별점 이미지들
const svgStars = document.querySelectorAll("div.star-btn-wrap svg path");

reviewStarBtns.forEach((reviewStarBtn) => {
    reviewStarBtn.addEventListener("click", (e) => {
        // 색깔 채워주기
        for (let i = 0; i < 5; i++) {
            if (i <= reviewStarBtn.dataset.point) {
                svgStars[i].style.fill = "#FFD633";
            } else {
                svgStars[i].style.fill = "#E0E0E0";
            }
        }
        // 나중에 별점 폼으로 넘겨주기 위해서
        document.getElementById("rate").value =
            parseInt(reviewStarBtn.dataset.point) + 1;
    });
});