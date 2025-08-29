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

    function generateRandomString(length = 6) {
        const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        let result = '';
        for (let i = 0; i < length; i++) {
            const idx = Math.floor(Math.random() * chars.length);
            result += chars[idx];
        }
        return result;
    }

    // 리뷰글 렌더링
    const renderReviewList = (reviews = []) => {
        const reviewList = getElement("#review-list");
        const noResult = getElement(".no-review-result-wrap");

        if (!reviewList || !noResult) return;

        reviewList.innerHTML = "";

        if (!reviews.length) {
            noResult.style.display = "flex";   // 후기가 없으면 보이게
        } else {
            noResult.style.display = "none";   // 후기가 있으면 숨기기
        }

        const fragment = document.createDocumentFragment();
        reviews.forEach(review => {
            const li = document.createElement("li");
            li.innerHTML = `
            <div class="answer-list-container">
                <div class="answer-list-header-wrap">
                    <div class="answer-list-content-tag review-list-content-tag">
                        <div class="review-img-star">
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
                                <span class="review-content-text anonymous">회원_${generateRandomString()}</span>
                                <div class="answer-create-day write-date">${timeForToday(review.createdDatetime)}</div>
                            </div>
                                <span class="review-content-text content">${review.content}</span>
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
                const reviewBtnWrap = document.querySelector(".review-btn-wrap");

                if (cnt === "0") {
                    // 답변 탭
                    answerList.style.display = "block";
                    answerPaging.style.display = "block";
                    reviewList.style.display = "none";
                    reviewPaging.style.display = "none";
                    noAnswer.style.display = "none";
                    noReview.style.display = "none";
                    reviewBtnWrap.style.display = "none";

                } else if (cnt === "1") {
                    // 리뷰 탭
                    answerList.style.display = "none";
                    answerPaging.style.display = "none";
                    reviewList.style.display = "block";
                    reviewPaging.style.display = "block";
                    noAnswer.style.display = "none";
                    noReview.style.display = "flex";
                    reviewBtnWrap.style.display = "flex";

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
                        doctorLayout.renderAnswerList(answers);
                        doctorLayout.renderReplyPaging(detailDTO.criteria, doctorData, doctorId, currentMemberId);
                    } else if (cnt === "1") {
                        const reviews = (doctorData.reviews || []).map(r => ({
                            content: r.content || "-",
                            rating: r.rating || 0,
                            createdDatetime: r.createdDatetime || null,
                            memberProfile: r.memberProfile || null
                        }));
                        doctorLayout.renderReviewList(reviews);
                        doctorLayout.renderReviewPaging(detailDTO.criteria, doctorData, doctorId, currentMemberId);
                    }
                } catch (err) {
                    console.error(cnt === "0" ? "답변 리스트 로딩 실패:" : "리뷰 리스트 로딩 실패:", err);
                }
            });
        });
    });

    document.addEventListener("DOMContentLoaded", () => {
        const pathParts = window.location.pathname.split("/");
        const doctorId = Number(pathParts[3]);

        const reviewBtn = document.querySelector(".exist-review-btn");
        const reviewRegisterContainer = document.querySelector(".review-register-container.v2");
        const reviewListContainer = document.querySelector(".review-list");

        // 별점 클릭 이벤트
        const setupStarRating = (form) => {
            if (!form) return;
            const stars = form.querySelectorAll(".star-list-content");
            const rateInput = form.querySelector("input[name='rate']");
            if (!rateInput) return;

            stars.forEach((li) => {
                const btn = li.querySelector("button");
                btn.addEventListener("click", () => {
                    const point = li.dataset.point;
                    rateInput.value = Number(point) + 1; // 1~5점
                    stars.forEach((s, idx) => {
                        const svg = s.querySelector("svg path");
                        if (idx <= point) svg.setAttribute("fill", "#FFD600");
                        else svg.setAttribute("fill", "#E0E0E0");
                    });
                });
            });
        };

        setupStarRating(reviewRegisterContainer);

        // 후기 버튼 클릭 시
        if (reviewBtn) {
            reviewBtn.addEventListener("click", async () => {
                try {
                    const response = await fetch(`/api/review/check?doctorId=${doctorId}&memberId=${currentMemberId}`);
                    if (!response.ok) throw new Error("방문진료 API 호출 실패");
                    const result = await response.json();

                    if (!result.hasVisited) {
                        alert("방문진료 기록이 있는 회원만 후기를 작성할 수 있습니다.");
                        if (reviewRegisterContainer) reviewRegisterContainer.style.display = "none";
                        return;
                    }

                    const existResp = await fetch(`/api/review/exists?doctorId=${doctorId}&memberId=${currentMemberId}`);
                    const existResult = await existResp.json();
                    if (existResult.exists) {
                        alert("이미 리뷰를 작성하셨습니다.");
                        if (reviewRegisterContainer) reviewRegisterContainer.style.display = "none";
                        return;
                    }

                    if (reviewRegisterContainer) reviewRegisterContainer.style.display = "block";
                } catch (err) {
                    console.error(err);
                    alert("후기 작성 검증 중 오류가 발생했습니다.");
                }
            });
        }

        // 후기 등록 처리 (V2 폼)
        if (reviewRegisterContainer) {
            reviewRegisterContainer.addEventListener("submit", async (e) => {
                e.preventDefault();

                const contentInput = reviewRegisterContainer.querySelector("[name='reviewContent']");
                const rateInput = reviewRegisterContainer.querySelector("[name='rate']");
                const content = contentInput?.value.trim() || "";
                const rating = Number(rateInput?.value) || 0;

                if (!content) {
                    alert("후기 내용을 입력해주세요.");
                    return;
                }
                if (rating <= 0) {
                    alert("별점을 선택해주세요.");
                    return;
                }

                if (!confirm("한번 등록한 리뷰는 수정 및 삭제가 불가능합니다.\n등록하시겠습니까?")) return;

                try {
                    const response = await fetch("/api/review/insert", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({
                            memberId: currentMemberId,
                            doctorId: doctorId,
                            content,
                            rating
                        })
                    });

                    if (!response.ok) {
                        const errorText = await response.text();
                        throw new Error(errorText || "후기 등록 실패");
                    }

                    // 폼 초기화
                    contentInput.value = "";
                    rateInput.value = 0;
                    reviewRegisterContainer.querySelectorAll(".star-list-content svg path")
                        .forEach(path => path.setAttribute("fill", "#E0E0E0"));

                    alert("후기 등록 완료!");
                    window.location.reload();

                } catch (err) {
                    console.error(err);
                    alert(err.message);
                }
            });
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