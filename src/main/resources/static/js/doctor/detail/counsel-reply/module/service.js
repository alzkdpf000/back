const doctorService = (() => {
    const getDoctorDetail = async (doctorId, page) => {
        const res = await fetch(`/api/doctors/detail/${doctorId}/${page}`);
        if (!res.ok) throw new Error("의사 상세 정보 불러오기 실패");
        return await res.json();
    };

    const toggleLike = async (doctorId, memberId) => {
        const res = await fetch(`/likes/toggle`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ doctorId, memberId })
        });

        if (!res.ok) throw new Error("좋아요 토글 실패");
        return await res.text();
    };

    return { getDoctorDetail: getDoctorDetail, toggleLike: toggleLike };
})();