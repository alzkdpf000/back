const doctorLayout = (() => {
    const getElement = (selector) => document.querySelector(selector);

    const renderDoctorInfo = (doctor) => {
        const container = getElement("#doctor-detail-container");
        if (!container || !doctor) return;

        container.innerHTML = `
        <div class="info-layout-container">
            <div class="info-main-layout-wrap">
                <div class="info-header-layout">
                    <div class="info-header-wrap">
                        <img class="info-profil-img" src="${doctor.profileImg || 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp'}" width="140" height="140">
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
                            <button class="like-btn">
                                <img alt="관심토픽 이미지" loading="lazy" width="25" height="25" src="${doctor.liked ? '/images/heart.png' : '/images/heart-empty.png'}">
                                <span class="doctorinfo-favoriteCount">${doctor.likesCount || 0}</span><span class="unit">명</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        `;
    };

    const renderAnswerList = (answers = []) => {
        const answerList = getElement("#intersectionObserver");
        const noResult = getElement(".no-search-result-wrap");
        if (!answerList || !noResult) return;

        answerList.innerHTML = "";

        if (!answers.length) {
            noResult.style.display = "flex";
            return;
        } else {
            noResult.style.display = "none";
        }

        const fragment = document.createDocumentFragment();
        answers.forEach(answer => {
            const li = document.createElement("li");

            // 카테고리가 비어있으면 '-' 표시
            const categoryText = answer.category && answer.category.trim() ? answer.category : '-';

            li.innerHTML = `
                <a class="link-click">
                    <div class="answer-list-container">
                        <div class="answer-list-header-wrap">
                            <div class="answer-list-content-tag">
                                <span>${categoryText}</span>
                            </div>
                            <div class="answer-create-day">${answer.createDate || '-'}</div>
                        </div>
                        <div class="answer-title-content-wrap">
                            <span class="answer-title-content">${answer.title || '-'}</span>
                            <span class="answer-content-wrap"><span class="answer-content">A.</span>${answer.content || '-'}</span>
                        </div>
                    </div>
                </a>
            `;
            fragment.appendChild(li);
        });
        answerList.appendChild(fragment);
    };

    const loadDoctorDetailFromDTO = (detailDTO) => {
        if (!detailDTO?.doctorsDetail?.length) return;

        const doctorData = detailDTO.doctorsDetail[0];

        // 의사 정보
        const doctor = {
            id: doctorData.doctorListDTO.memberId,
            name: doctorData.doctorListDTO.memberName,
            profileImg: doctorData.doctorListDTO.memberKakaoProfileUrl,
            specialty: doctorData.doctorListDTO.doctorSpecialty,
            hospital: doctorData.doctorListDTO.hospitalName,
            liked: doctorData.liked || false,
            likesCount: doctorData.likesCount || 0,
            detailAddress: doctorData.doctorListDTO.hospitalAddress,
            hospitalPhone: doctorData.doctorListDTO.hospitalPhone
        };

        renderDoctorInfo(doctor);

        // 답변 목록
        const answers = (doctorData.counselReplyDTOList || []).map(reply => {
            return {
                category: Array.isArray(reply.categoryNames) ? reply.categoryNames.join(', ') : '-',
                title: reply.consultationPostTitle || '-',
                content: reply.counselReplyContent || '-',
                createDate: reply.createdDatetime?.split(' ')[0] || '-'
            };
        });
        console.log(answers);

        renderAnswerList(answers);
    };

    return { renderDoctorInfo:renderDoctorInfo, renderAnswerList: renderAnswerList, loadDoctorDetailFromDTO:loadDoctorDetailFromDTO };
})();
