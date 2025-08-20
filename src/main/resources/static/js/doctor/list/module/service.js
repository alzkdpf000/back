const doctorService = (() => {

    const getDoctors = async (page = 1, currentMemberId = 1) => {
        let url = `/api/doctors/list/${page}?currentMemberId=${currentMemberId}`;
        const res = await fetch(url);
        if (!res.ok) throw new Error(`의사 목록 로드 실패: ${res.status}`);
        return await res.json();
    };

    const toggleLike = async (doctorId, memberId = 1) => {
        const res = await fetch('/likes/toggle', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ doctorId, memberId: 1 })
        });
        if (!res.ok) throw new Error("좋아요 토글 실패");
        return await res.text();
    };


    return { getDoctors: getDoctors, toggleLike: toggleLike };
})();