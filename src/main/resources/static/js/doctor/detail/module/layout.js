window.doctorLayout = (() => {
    // ===================== 공통 설정 =====================
    HTMLCollection.prototype.forEach = Array.prototype.forEach;
    let modalCheck;

    document.addEventListener("DOMContentLoaded", async () => {
        await window.fetchCurrentMember();
        doctorLayout.loadDoctorDetailFromDTO(1);
    });

// ===================== 모달 관련 함수 =====================
    document.querySelector("div.modal").addEventListener("click", () => {
        if (!modalCheck) return;
        document.querySelector("div.warn-modal").style.animation = "popDown 0.5s";
        setTimeout(() => { document.querySelector("div.modal").style.display = "none"; }, 450);
    });

    const getElement = (selector) => document.querySelector(selector);

    // 상대 시간 변환
    const timeForToday = (datetime) => {
        const today = new Date();
        const date = new Date(datetime);
        let gap = Math.floor((today.getTime() - date.getTime()) / 1000 / 60);

        if (gap < 1) return "방금 전";
        if (gap < 60) return `${gap}분 전`;

        gap = Math.floor(gap / 60);
        if (gap < 24) return `${gap}시간 전`;

        gap = Math.floor(gap / 24);
        if (gap < 31) return `${gap}일 전`;

        gap = Math.floor(gap / 31);
        if (gap < 12) return `${gap}개월 전`;

        gap = Math.floor(gap / 12);
        return `${gap}년 전`;
    };

    // 의사 정보 렌더링 + 좋아요 버튼 이벤트
    const renderDoctorInfo = (doctor) => {
        const container = getElement("#doctor-detail-container");
        if (!container || !doctor) return;

        container.innerHTML = `
        <div class="info-layout-container">
            <div class="info-main-layout-wrap">
                <div class="info-header-layout">
                    <div class="info-header-wrap">
                        <img class="info-profil-img" 
                             src="${doctor.profileImg || 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp'}" 
                             width="140" height="140">
                        <div class="info-profil-text-wrap">
                            <div class="info-profil-name-wrap">
                                <span class="info-profil-name">${doctor.name || '이름 없음'}</span>
                            </div>
                            <div class="info-profil-span-wrap">
                                <span class="info-profil-introduction department">담당 과</span>
                                <span class="info-profil-introduction department-content">${doctor.specialty || '없음'}</span>
                            </div>
                            <div class="info-profil-span-wrap address">
                                <span class="info-profil-introduction department">소속 병원</span>
                                <span class="info-profil-introduction department-content">${doctor.hospital || '없음'}</span>
                            </div>
                            <div class="info-profil-span-wrap address">
                                <span class="info-profil-introduction department">상세 주소</span>
                                <span class="info-profil-introduction department-content">${doctor.detailAddress || '없음'}</span>
                            </div>
                            <div class="info-profil-introduction">
                                <span class="info-profil-introduction department">병원 전화 번호</span>
                                <span class="info-profil-introduction department-content">${doctor.hospitalPhone || '없음'}</span>
                            </div>
                        </div>
                        <div class="docterinfo-favorite-wrapper">
                            <button class="like-btn" data-doctor-id="${doctor.id}" data-liked="${doctor.liked ? 'true' : 'false'}">
                                <img alt="관심토픽 이미지" loading="lazy" width="25" height="25"
                                     src="${doctor.liked ? '/images/heart.png' : '/images/heart-empty.png'}">
                                <span class="doctorinfo-favoriteCount">${doctor.likesCount ?? 0}</span><span class="unit">명</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        `;

        const showWarnModal = (modalMessage) => {
            const modalCheckDelay = 500;
            document.getElementById("content-wrap").innerHTML = modalMessage;
            const warnModal = document.querySelector("div.warn-modal");
            const modal = document.querySelector("div.modal");
            warnModal.style.animation = "popUp 0.5s";
            modal.style.display = "flex";
            setTimeout(() => { modalCheck = true; }, modalCheckDelay);

            modal.addEventListener("click", () => {
                warnModal.style.animation = "popDown 0.5s";
                setTimeout(() => { modal.style.display = "none"; }, 450);
            });
        };

        const showLikeModal = (modalMessage) => {
            const likeModal = getElement("div.like-modal");
            const warnText = likeModal.querySelector(".warn-text");
            warnText.textContent = modalMessage;

            likeModal.style.display = "flex";
            likeModal.style.animation = "popUp 0.5s";

            setTimeout(() => {
                likeModal.style.animation = "popDown 0.5s";
                setTimeout(() => { likeModal.style.display = "none"; }, 450);
            }, 1000);
        };

        const showReviewModal = (modalMessage) => {
            const reviewModal = getElement("div.review-modal");
            const warnText = reviewModal.querySelector(".warn-text");
            warnText.textContent = modalMessage;

            reviewModal.style.display = "flex";
            reviewModal.style.animation = "popUp 0.5s";

            setTimeout(() => {
                reviewModal.style.animation = "popDown 0.5s";
                setTimeout(() => { reviewModal.style.display = "none"; }, 450);
            }, 1000);
        };

        // 좋아요 버튼 연결
        container.querySelectorAll(".like-btn").forEach(btn => {
            btn.addEventListener("click", async (e) => {
                e.preventDefault();
                e.stopPropagation();

                if (!currentMemberId) { // 로그인 안 됨
                    showWarnModal("로그인 후 이용해주세요.");
                    return;
                }

                const doctorId = Number(btn.dataset.doctorId);
                const wrapper = btn.closest(".docterinfo-favorite-wrapper");
                const countSpan = wrapper.querySelector(".doctorinfo-favoriteCount");

                try {
                    const result = await doctorService.toggleLike(doctorId, currentMemberId);
                    const img = btn.querySelector("img");
                    if (result === "liked") {
                        img.src = '/images/heart.png';
                        countSpan.textContent = Number(countSpan.textContent) + 1;
                        showWarnModal("내 관심 의사로 등록했습니다.");
                    } else if (result === "unliked") {
                        img.src = '/images/heart-empty.png';
                        countSpan.textContent = Number(countSpan.textContent) - 1;
                        showWarnModal("내 관심 의사에서 해제되었습니다.");
                    }
                } catch(err) {
                    console.error("좋아요 토글 실패:", err);
                    showLikeModal("로그인 후 이용해주세요.");
                }
            });
        });
    };

    // 답변글 렌더링
    const renderAnswerList = (answers = []) => {
        const answerList = getElement("#intersectionObserver");
        const noResult = getElement(".no-search-result-wrap");
        if (!answerList || !noResult) return;

        answerList.innerHTML = "";
        if (!answers.length) {
            noResult.style.display = "flex";
            return;
        } else noResult.style.display = "none";

        const fragment = document.createDocumentFragment();
        answers.forEach(answer => {
            const li = document.createElement("li");
            li.innerHTML = `
                <a class="link-click">
                    <div class="answer-list-container">
                        <div class="answer-list-header-wrap">
                            <div class="answer-list-content-tag">
                                <span>${answer.category || '-'}</span>
                            </div>
                            <div class="answer-create-day">${timeForToday(answer.createDate)}</div>
                            <div class="answer-title-content-wrap">
                                <span class="answer-title-content">${answer.title || '-'}</span>
                                <span class="answer-content-wrap"><span class="answer-content">A.</span>${answer.content || '-'}</span>
                            </div>
                        </div>
                    </div>
                </a>
            `;
            fragment.appendChild(li);
        });
        answerList.appendChild(fragment);
    };

    const renderReplyPaging = (criteria, doctorData, doctorId, memberId) => {
        const pageContainer = getElement("#page-container");
        if (!criteria || !pageContainer) return;
        const totalReplies = (doctorData.counselReplyDTOList || []).length;
        if (totalReplies === 0) {
            pageContainer.innerHTML = "";
            return;
        }

        let html = "";
        if(criteria.hasPreviousPage) html += `<a href="#" data-page="${criteria.startPage - 1}">이전</a>`;
        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            html += `<a href="#" data-page="${i}">${i}</a>`;
        }
        if(criteria.hasNextPage) html += `<a href="#" data-page="${criteria.endPage + 1}">다음</a>`;
        pageContainer.innerHTML = html;

        pageContainer.querySelectorAll("a").forEach(link => {
            link.addEventListener("click", async e => {
                e.preventDefault();
                const page = Number(link.dataset.page);
                try {
                    const detailDTO = await doctorService.getDoctorDetail(doctorId, page);
                    const updatedDoctorData = detailDTO.doctorsDetail[0];
                    const answers = (updatedDoctorData.counselReplyDTOList || []).map(reply => ({
                        category: Array.isArray(reply.categoryNames) ? reply.categoryNames.join(", ") : "-",
                        title: reply.consultationPostTitle || "-",
                        content: reply.counselReplyContent || "-",
                        createDate: reply.createdDatetime || null
                    }));
                    renderAnswerList(answers);
                    renderReplyPaging(detailDTO.criteria, updatedDoctorData, doctorId, memberId);
                } catch(err) {
                    console.error("답변 리스트 로딩 실패:", err);
                }
            });
        });
    };

    document.addEventListener("DOMContentLoaded", async () => {
        // URL에서 doctorId와 page 추출
        const pathParts = window.location.pathname.split("/");
        const doctorId = pathParts[3]; // 0:/, 1:doctor, 2:detail, 3:doctorId
        const page = Number(pathParts[4]) || 1;

        if (!doctorId) {
            console.error("URL에서 doctorId를 찾을 수 없습니다.");
            return;
        }

        try {
            // API 호출
            const detailDTO = await doctorService.getDoctorDetail(doctorId, page);
            if (!detailDTO?.doctorsDetail?.length) {
                console.error("의사 상세 정보가 없습니다.");
                return;
            }

            // 의사 상세 정보 렌더링
            doctorLayout.loadDoctorDetailFromDTO(detailDTO, currentMemberId);

        } catch (err) {
            console.error("의사 상세 정보 불러오기 실패:", err);
        }
    });

    const loadDoctorDetailFromDTO = (detailDTO, memberId) => {
        if (!detailDTO?.doctorsDetail?.length) return;
        const doctorData = detailDTO.doctorsDetail[0];

        const doctor = {
            id: doctorData.doctorListDTO.memberId,
            name: doctorData.doctorListDTO.memberName,
            profileImg: doctorData.doctorListDTO.memberKakaoProfileUrl,
            specialty: doctorData.doctorListDTO.doctorSpecialty,
            hospital: doctorData.doctorListDTO.hospitalName,
            liked: doctorData.doctorListDTO.liked || false,
            likesCount: doctorData.doctorListDTO.likesCount || 0,
            detailAddress: doctorData.doctorListDTO.hospitalAddress,
            hospitalPhone: doctorData.doctorListDTO.hospitalPhone
        };

        renderDoctorInfo(doctor, memberId);

        const answers = (doctorData.counselReplyDTOList || []).map(reply => ({
            category: Array.isArray(reply.categoryNames) ? reply.categoryNames.join(", ") : "-",
            title: reply.consultationPostTitle || "-",
            content: reply.counselReplyContent || "-",
            createDate: reply.createdDatetime || null
        }));

        renderAnswerList(answers);
        renderReplyPaging(detailDTO.criteria, doctorData, doctor.id, memberId);
    };

    return {
        renderDoctorInfo: renderDoctorInfo,
        renderAnswerList: renderAnswerList,
        renderReplyPaging: renderReplyPaging,
        loadDoctorDetailFromDTO: loadDoctorDetailFromDTO// DTO 로딩도 반환
    };
})();