async function addLike(memberId, doctorId) {
    const response = await fetch("/likes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ memberId, doctorId })
    });
    if (!response.ok) throw new Error("좋아요 등록 실패");
    return await response.text();
}

// 좋아요 삭제
async function removeLike(memberId, doctorId) {
    const response = await fetch(`/likes/member/${memberId}/doctor/${doctorId}`, {
        method: "DELETE"
    });
    if (!response.ok) throw new Error("좋아요 삭제 실패");
    return await response.text();
}

// 좋아요 수 조회
async function getLikesCountByDoctor(doctorId) {
    const response = await fetch(`/likes/doctor/${doctorId}/count`);
    if (!response.ok) throw new Error("좋아요 수 조회 실패");
    return await response.json();
}