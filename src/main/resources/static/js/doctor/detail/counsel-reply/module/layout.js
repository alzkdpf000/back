const doctorLayout = (() => {
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
    const renderDoctorInfo = (doctor, memberId) => {
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

        const currentMemberId = 31;

        // 좋아요 버튼 연결
        const likeBtn = document.querySelector(".like-btn");
        if (likeBtn) {
            const countSpan = likeBtn.querySelector(".doctorinfo-favoriteCount");
            likeBtn.addEventListener("click", async (e) => {
                const doctorId = Number(likeBtn.dataset.doctorId);
                try {
                    const result = await doctorService.toggleLike(doctorId, currentMemberId);
                    if (result === "liked") {
                        likeBtn.querySelector("img").src = '/images/heart.png';
                        countSpan.textContent = Number(countSpan.textContent) + 1;
                        likeBtn.dataset.liked = "true";
                    } else if (result === "unliked") {
                        likeBtn.querySelector("img").src = '/images/heart-empty.png';
                        countSpan.textContent = Number(countSpan.textContent) - 1;
                        likeBtn.dataset.liked = "false";
                    }
                } catch (err) {
                    console.error("좋아요 토글 실패:", err);
                    showWarnModal("로그인 후 이용해주세요.");
                }
            });
        }
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

    // 리뷰글 렌더링
    const renderReviewList = (reviews = []) => {
        const reviewList = getElement("#review-list");
        const noResult = getElement(".no-review-result-wrap");

        if (!reviewList || !noResult) return;

        reviewList.innerHTML = "";
        if (!reviews.length) {
            noResult.style.display = "flex";
            return;
        } else noResult.style.display = "none";

        const fragment = document.createDocumentFragment();
        reviews.forEach(review => {
            const li = document.createElement("li");
            li.innerHTML = `
            <div class="answer-list-container">
                <div class="answer-list-header-wrap">
                    <div class="answer-list-content-tag review-list-content-tag">
                        <div class="review-img-star">
                            <img class="info-profil-img review" src="${review.memberProfile || 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp'}" width="35" height="35">
                            <div class="review-star-wrap">
                                <div class="review-star-content">
                                    <div class="review-star-svg">
                                        <svg fill="none" height="40" viewBox="0 0 40 40" width="40" xmlns="http://www.w3.org/2000/svg">
                                            <path clip-rule="evenodd" d="M21.6515 4.43374L25.6752 13.3856L35.0647 14.6201C36.6096 14.8231 37.2093 16.8084 36.0899 17.907L29.1848 24.675L30.9639 34.3941C31.2566 35.9898 29.6374 37.2019 28.291 36.4257L19.9995 31.6536L11.708 36.4257C10.3601 37.2019 8.74241 35.9898 9.03512 34.3941L10.8142 24.675L3.91058 17.907C2.79117 16.8084 3.38942 14.8231 4.93575 14.6201L14.3253 13.3856L18.3475 4.43374C19.0071 2.96642 20.9918 2.96642 21.6515 4.43374Z" fill="#FFD633" fill-rule="evenodd"></path>
                                        </svg>
                                    </div>
                                    <span class="review-point">${review.rating.toFixed(1)}</span>
                                </div>
                            </div>
                        </div>
                        <div class="review-content-text-wrap">
                            <div class="review-header">
                                <span class="review-content-text anonymous">익명</span>
                                <div class="answer-create-day write-date">${timeForToday(review.createdDatetime)}</div>
                            </div>
                            <span class="review-content-text content">${review.content || '-'}</span>
                        </div>
                    </div>
                </div>
            </div>
        `;
            fragment.appendChild(li);
        });
        reviewList.appendChild(fragment);
    };

    const renderReviewPaging = (criteria, doctorData, doctorId, memberId) => {
        const pageContainer = getElement("#review-page-container");
        if (!criteria || !pageContainer) return;
        const totalReviews = (doctorData.reviews || []).length;
        if (totalReviews === 0) {
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
                    renderReviewList(updatedDoctorData.reviews);
                    renderReviewPaging(detailDTO.criteria, updatedDoctorData, doctorId, memberId);
                } catch(err) {
                    console.error("리뷰 리스트 로딩 실패:", err);
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

        const currentMemberId = 31;

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
        // 탭 클릭 이벤트 처리
        const profilBtns = document.querySelectorAll(".profil-content-select");
        const profilCotentCheck = document.querySelector(".profil-content-selector");


        profilBtns.forEach((profilBtn) => {
            profilBtn.addEventListener("click", async () => {
                const cnt = profilBtn.dataset.cnt;

                profilCotentCheck.style.transform = `translateX(calc(${100 * cnt}% + ${16 * cnt}px))`;
                document.querySelector("span.active").classList.remove("active");
                const spanTag = profilBtn.firstElementChild.firstElementChild;
                spanTag.classList.add("active");

                // 탭 전환 시 영역과 페이징 토글
                const answerList = document.querySelector("#intersectionObserver");
                const reviewList = document.querySelector("#review-list");
                const answerPaging = document.querySelector("#page-container");
                const reviewPaging = document.querySelector("#review-page-container");
                const noAnswer = document.querySelector(".no-search-result-wrap");
                const noReview = document.querySelector(".no-review-result-wrap");

                if (cnt === "0") {
                    // 답변 탭
                    answerList.style.display = "block";
                    answerPaging.style.display = "block";
                    reviewList.style.display = "none";
                    reviewPaging.style.display = "none";
                    noAnswer.style.display = "none";
                    noReview.style.display = "none";
                } else if (cnt === "1") {
                    // 리뷰 탭
                    answerList.style.display = "none";
                    answerPaging.style.display = "none";
                    reviewList.style.display = "block";
                    reviewPaging.style.display = "block";
                    noAnswer.style.display = "none";
                    noReview.style.display = "none"
                }

                try {
                    const detailDTO = await doctorService.getDoctorDetail(doctorId, 1);
                    const doctorData = detailDTO.doctorsDetail[0];

                    if (cnt === "0") {
                        const answers = (doctorData.counselReplyDTOList || []).map(reply => ({
                            category: Array.isArray(reply.categoryNames) ? reply.categoryNames.join(", ") : "-",
                            title: reply.consultationPostTitle || "-",
                            content: reply.counselReplyContent || "-",
                            createDate: reply.createdDatetime || null
                        }));
                        doctorLayout.renderAnswerList(answers);  // 내부에서 no-search-result-wrap 처리됨
                        doctorLayout.renderReplyPaging(detailDTO.criteria, doctorData, doctorId, currentMemberId);
                    } else if (cnt === "1") {
                        const reviews = (doctorData.reviews || []).map(r => ({
                            content: r.content || "-",
                            rating: r.rating || 0,
                            createdDatetime: r.createdDatetime || null,
                            memberProfile: r.memberProfile || null
                        }));
                        doctorLayout.renderReviewList(reviews);  // 내부에서 no-review-result-wrap 처리됨
                        doctorLayout.renderReviewPaging(detailDTO.criteria, doctorData, doctorId, currentMemberId);
                    }
                } catch (err) {
                    console.error(cnt === "0" ? "답변 리스트 로딩 실패:" : "리뷰 리스트 로딩 실패:", err);
                }
            });
        });
    });

    const reviewBtn = document.querySelector(".exist-review-btn");
    const reviewInputContainer = document.querySelector(".review-input-container");

    reviewBtn.addEventListener("click", async () => {
        try {
            const response = await fetch(`/api/review/check?doctorId=${doctorId}&memberId=${currentMemberId}`);
            const canWrite = await response.json();

            if(!canWrite){
                alert("방문진료 기록이 있는 회원만 후기를 작성할 수 있습니다.");
                return;
            }

            // 입력창 생성
            reviewInputContainer.innerHTML = `
            <textarea id="review-content" placeholder="후기를 작성해주세요..." rows="4" style="width:100%; margin-bottom:8px;"></textarea>
            <input type="number" id="review-rating" placeholder="평점 (0~5)" min="0" max="5" style="width:100px; margin-bottom:8px;">
            <button id="submit-review">작성 완료</button>
        `;
            reviewInputContainer.style.display = "block";

            // 작성 완료 클릭 이벤트
            document.getElementById("submit-review").addEventListener("click", async () => {
                const content = document.getElementById("review-content").value.trim();
                const rating = Number(document.getElementById("review-rating").value);

                if(!content || rating < 0 || rating > 5){
                    alert("내용과 평점을 올바르게 입력해주세요.");
                    return;
                }

                try {
                    const res = await fetch("/api/review/write", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({
                            doctorId,
                            memberId: currentMemberId,
                            content,
                            rating
                        })
                    });

                    if(res.ok){
                        alert("후기가 등록되었습니다.");
                        reviewInputContainer.style.display = "none";
                        // 필요 시 리뷰 목록 갱신
                    }
                } catch(err){
                    console.error(err);
                    alert("후기 등록 중 오류가 발생했습니다.");
                }
            });

        } catch (err) {
            console.error(err);
            alert("후기 작성 검증 중 오류가 발생했습니다.");
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
        renderReviewList: renderReviewList,
        renderReplyPaging: renderReplyPaging,
        renderReviewPaging: renderReviewPaging,
        loadDoctorDetailFromDTO: loadDoctorDetailFromDTO  // DTO 로딩도 반환
    };
})();