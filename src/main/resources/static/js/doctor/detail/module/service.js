// service.js 상단
window.currentMemberId = null; // 전역 변수

window.fetchCurrentMember = async () => { // 전역 함수
    try {
        const res = await fetch("/api/member/me");
        if (!res.ok) throw new Error("로그인 필요");
        const member = await res.json();
        window.currentMemberId = member.id;
    } catch(e) {
        console.warn("로그인 안됨", e);
        window.currentMemberId = 0;
    }
};

const doctorService = (() => {
    const getDoctorDetail = async (doctorId, page) => {
        const res = await fetch(`/api/doctors/detail/${doctorId}/${page}`);
        if (!res.ok) throw new Error("의사 상세 정보 불러오기 실패");
        return await res.json();
    };

    const toggleLike = async (doctorId) => {
        const res = await fetch(`/likes/toggle`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ doctorId })
        });

        if (!res.ok) throw new Error("좋아요 토글 실패");
        return await res.text();
    };



    return { getDoctorDetail: getDoctorDetail, toggleLike: toggleLike };
})();