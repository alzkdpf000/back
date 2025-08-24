document.addEventListener("DOMContentLoaded", async () => {
    const doctorId = 1;
    const page = 1;
    const currentMemberId = 1; // 임시 로그인 ID

    try {
        const detailDTO = await doctorService.getDoctorDetail(doctorId, page);
        const doctorData = detailDTO.doctorsDetail[0];
        const dto = doctorData.doctorListDTO;

        // 의사 정보 렌더링
        doctorLayout.renderDoctorInfo({
            id: dto.memberId,
            name: dto.memberName,
            profileImg: dto.memberKakaoProfileUrl,
            specialty: dto.doctorSpecialty,
            hospital: dto.hospitalName,
            liked: dto.liked,
            likesCount: dto.likesCount || 0,
            detailAddress: dto.hospitalAddress,
            hospitalPhone: dto.hospitalPhone
        });

        // 답변 리스트
        doctorLayout.renderAnswerList(
            (doctorData.counselReplyDTOList || []).map(reply => ({
                category: reply.consultationPostTitle || '',
                title: reply.consultationPostTitle || '',
                content: reply.counselReplyContent,
                createDate: reply.createdDatetime?.split(' ')[0] || ''
            }))
        );

        // 좋아요 버튼 이벤트
        const likeBtn = document.querySelector(".like-btn");
        const img = likeBtn.querySelector("img");
        const countSpan = document.querySelector(".doctorinfo-favoriteCount");

        likeBtn.addEventListener("click", async () => {
            try {
                const result = await doctorService.toggleLike(doctorId, currentMemberId);

                let currentCount = Number(countSpan.textContent);
                if (result === "liked") {
                    img.src = "/images/heart.png";
                    countSpan.textContent = currentCount + 1;
                } else {
                    img.src = "/images/heart-empty.png";
                    countSpan.textContent = currentCount - 1;
                }
            } catch (err) {
                console.error("좋아요 토글 실패:", err);
                showWarnModal("로그인 후 이용해주세요.");
            }
        });

    } catch (e) {
        console.error("의사 상세 정보 로딩 실패:", e);
    }
});