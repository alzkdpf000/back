const doctorService = (() => {

    const getDoctors = async (page = 1) => {
        const res = await fetch(`/api/doctor/list/${page}`);
        if (!res.ok) throw new Error(`의사 목록 로드 실패: ${res.status}`);
        return await res.json();
    };

    const toggleLike = async (doctorId, memberId) => {
        const res = await fetch('/likes/toggle', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ doctorId, memberId })
        });
        if (!res.ok) throw new Error("좋아요 토글 실패");
        return await res.text(); // "liked" or "unliked"
    };

    const getLikesCount = async (doctorId) => {
        const res = await fetch(`/likes/count/${doctorId}`);
        if (!res.ok) throw new Error("좋아요 수 조회 실패");
        return await res.json();
    };

    return { getDoctors, toggleLike, getLikesCount };
})();