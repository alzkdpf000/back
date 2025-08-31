const doctorLayout = (() => {

    const renderDoctorInfo = (doctor) => {
        const container = document.getElementById("doctor-detail-container");
        container.innerHTML = `
            <div class="info-header-wrap">
                <div class="info-profile">
                    <img src="${doctor.profileImg}" alt="의사 프로필 이미지" width="80" height="80">
                    <div class="info-name-like-wrap">
                        <h2 class="info-name">${doctor.name}</h2>
                        <button class="like-btn" data-doctor-id="${doctor.id}">
                            <img src="${doctor.liked ? '/images/heart.png' : '/images/heart-empty.png'}" width="25" height="25">
                        </button>
                    </div>
                </div>
                <div class="info-specialty">
                    <span>${doctor.specialty}</span>
                    <span>${doctor.hospital}</span>
                </div>
            </div>
        `;
    };

    const renderAnswerList = (answers) => {
        const answerList = document.getElementById("intersectionObserver");
        const noResult = document.querySelector(".no-search-result-wrap");
        answerList.innerHTML = "";

        if (!answers || answers.length === 0) {
            noResult.style.display = "flex";
        } else {
            noResult.style.display = "none";
            answers.forEach(answer => {
                const li = document.createElement("li");
                li.innerHTML = `
                    <a>
                        <div class="answer-list-container">
                            <div class="answer-list-header-wrap">
                                <div class="answer-list-content-tag">
                                    <span>${answer.category}</span>
                                </div>
                                <div class="answer-create-day write-date">
                                    ${answer.createDate}
                                </div>
                            </div>
                            <div class="answer-title-content-wrap">
                                <span class="answer-title-content">${answer.title}</span>
                                <span class="answer-content-wrap"><span class="answer-content">A.</span>${answer.content}</span>
                            </div>
                        </div>
                    </a>
                `;
                answerList.appendChild(li);
            });
        }
    };

    return { renderDoctorInfo: renderDoctorInfo, renderAnswerList: renderAnswerList };
})();