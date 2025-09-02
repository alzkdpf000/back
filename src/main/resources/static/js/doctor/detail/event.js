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

