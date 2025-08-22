document.addEventListener("DOMContentLoaded", async () => {
    const doctorId = 1;
    const page = 1;

    try {
        const detailDTO = await doctorService.getDoctorDetail(doctorId, page);

        const doctorData = detailDTO.doctorsDetail[0];

        doctorLayout.renderDoctorInfo({
            id: doctorData.doctorListDTO.memberId,
            name: doctorData.doctorListDTO.memberName,
            profileImg: doctorData.doctorListDTO.memberKakaoProfileUrl || '/images/default-profile.png',
            specialty: doctorData.doctorListDTO.doctorSpecialty,
            hospital: doctorData.hospitalName,
            liked: doctorData.liked
        });

        doctorLayout.renderAnswerList(
            doctorData.counselReplyDTOList.map(reply => ({
                category: reply.categoryName || '',
                title: reply.consultationPostTitle || '',
                content: reply.counselReplyContent,
                createDate: reply.createdDatetime.split(' ')[0]
            }))
        );

        const likeBtn = document.querySelector(".like-btn");
        likeBtn?.addEventListener("click", async () => {
            try {
                const res = await fetch(`/likes/toggle`, {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify({doctorId, memberId: 1})
                });
                if (!res.ok) throw new Error("좋아요 토글 실패");

                const img = likeBtn.querySelector("img");
                img.src = img.src.includes("heart-empty.png") ? "/images/heart.png" : "/images/heart-empty.png";

            } catch (e) {
                console.error(e);
            }
        });

    } catch (e) {
        console.error(e);
    }
});