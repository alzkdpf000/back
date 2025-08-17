const doctorLayout = (() => {
    const showDoctors = async (result, load) => {
        let text = ``;

        const doctors = result.doctorsList;
        const total = result.total;
        const criteria = result.criteria;
        const doctorCount = document.getElementById("doctorCount");
        const pageWrap = document.querySelector(".pagination.bootpay-pagination.doctor-pagination");
        const tbody = document.getElementById("doctorsTbody");
        doctorCount.textContent = total;
        if (doctors.length === 0 && !load) {
            text +=
                `
            <td class="text-center font-weight-bold" colspan="8" >회원이 존재하지 않습니다</td>
            `
        } else {
            doctors.forEach((doctor) => {
                const checkStatus = doctor.memberStatus === "ACTIVE";
                text += `
            <tr>
                            <td class="td-name" style="width: 10%;  text-align: center;">
                                <div class="member-name">${doctor.id}
                                </div>
                            </td>
                            <td class="td-amount text-right pr-4 font-weight-bold" style=" text-align: center; width: 10%">${doctor.memberName}
                                <span class="amount-unit"> 님</span>
                            </td>
                            <td class="td-email" style="width: 10%">${doctor.provider === "KAKAO" ? doctor.kakaoEmail : doctor.memberEmail}</td>
                            <td class="td-phone" style="width: 10%">${doctor.memberPhone}</td>
                            <td class="td-phone" style="width: 10%">${doctor.doctorLicenseNumber}</td>
                            <td class="td-start" style="width: 10%" >${doctor.createdDate}</td>
                            <td class="td-recent" style="width: 5%; color : ${checkStatus ? "#507cf3" : "#fe657e"}">${checkStatus ? "활동 중" : "탈퇴"}</td>
                            <td class="td-action text-center" style="width: 5%;">
                                <div class="action-btn member-detail-btn" data-doctorid = ${doctor.id}>
                                    <i class="mdi mdi-chevron-right"></i>
                                </div>
                            </td>
                        </tr>
            `
            })
        }
        tbody.innerHTML = text;
        text = ``;
        if (criteria.hasPreviousPage) {
            text = `
            <li class="page-item page-num">
                <a data-page="${criteria.startPage - 1}" class="paging">이전</a>
            </li>`

        }

        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            text += `
            <li class="page-item page-num ${i === criteria.page && "active"}">
                <a data-page="${i}" class="paging">${i}</a>
            </li>
        `;
        }

        if (criteria.hasNextPage) {
            text += `
            <li class="page-item page-num">
                <a data-page="${criteria.endPage + 1}" class="paging">다음</a>
            </li>`
        }
        pageWrap.innerHTML = text;


    }
    const showDoctorDetail = async (result) => {
        let text = ``;
        const memberDetailName = document.getElementById("memberDetailName");
        const memberDetailPhone = document.getElementById("memberDetailPhone");
        const memberDetailVita = document.getElementById("memberDetailVita");
        const memberDetailCreatedDatetime = document.getElementById("memberDetailCreatedDatetime");
        const memberDetailId = document.getElementById("memberDetailId");
        const memberDetailEmail = document.getElementById("memberDetailEmail");
        const memberDetailStatus = document.getElementById("memberDetailStatus");
        const memberDetailProvider = document.getElementById("memberDetailProvider");
        const memberDetailPost = document.getElementById("memberDetailPost");
        const posts = result.consultationPosts;
        const memberEmail = result.provider === "KAKAO" ? result.kakaoEmail : result.memberEmail;
        const memberStatus = result.memberStatus === "ACTIVE" ? "활동 중" : "탈퇴";
        memberDetailStatus.style.color = result.memberStatus === "ACTIVE" ? "#507cf3": "#fe657e";
        memberDetailName.textContent = result.memberName;
        memberDetailPhone.textContent = result.memberPhone;
        memberDetailVita.textContent = result.memberVitaAmount;
        memberDetailCreatedDatetime.textContent = result.createdDatetime;
        memberDetailId.textContent = result.id;
        memberDetailEmail.textContent = memberEmail;
        memberDetailStatus.textContent = memberStatus;
        memberDetailProvider.textContent = result.provider;
        if (posts.length === 0) {
            text +=
                `
                <tr><td class="text-light-grey text-center" colspan="4">작성한 상담글이 없습니다</td></tr>
                `
        } else {
            posts.forEach((post) => {
                const check = post.consultationPostStatus === "ACTIVE";
                text += `
            <tr>
                <td>${post.consultationPostTitle}</td>
                <td>${post.consultationPostAnswerCount}</td>
                <td style="color :${check ? "#507cf3": "#fe657e"}">${check ? "Y" : "N"}</td>
                <td>${post.createdDate}</td>
            </tr>`
            })
        }

        memberDetailPost.innerHTML = text;
    }
    return {showDoctors: showDoctors, showDoctorDetail: showDoctorDetail};
})();