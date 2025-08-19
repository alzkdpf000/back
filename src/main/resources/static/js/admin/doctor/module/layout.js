const doctorLayout = (() => {
    const showDoctors = async (result) => {
        let text = ``;

        const doctors = result.doctorsList;
        const total = result.total;
        const criteria = result.criteria;
        const doctorCount = document.getElementById("doctorCount");
        const pageWrap = document.querySelector(".pagination.bootpay-pagination.doctor-pagination");
        const tbody = document.getElementById("doctorsTbody");
        document.getElementById("doctorKeyword").value = result.search.keyword;
        doctorCount.textContent = total;
        if (doctors.length === 0) {
            text +=
                `
            <td class="text-center font-weight-bold" colspan="8" >회원이 존재하지 않습니다</td>
            `
        } else {
            doctors.forEach((doctor) => {
                let checkStatus;
                let memberStatus;
                console.log(doctor.memberStatus,doctor.doctorStatus);
                if (doctor.memberStatus === "INACTIVE" && doctor.doctorStatus === "INACTIVE") {
                    memberStatus = "승인 거절";
                    checkStatus = false;
                } else if (doctor.memberStatus === "ACTIVE" && doctor.doctorStatus ==="INACTIVE") {
                    memberStatus = "승인 대기";
                    checkStatus = true;
                } else if (doctor.memberStatus === "INACTIVE" && doctor.doctorStatus ==="ACTIVE"){
                    memberStatus = "탈퇴";
                    checkStatus = false;
                }else{
                    memberStatus ="활동 중";
                    checkStatus = true;
                }
                text += `
            <tr>
                            <td class="td-name" style="width: 10%;  text-align: center;">
                                <div class="member-name">${doctor.id}
                                </div>
                            </td>
                            <td class="td-amount text-right pr-4 font-weight-bold" style=" text-align: center; width: 10%">${doctor.memberName}
                                <span class="amount-unit"> 님</span>
                            </td>
                            <td class="td-email" style="width: 10%">
                            <span>${!doctor.memberEmail ? "없음" : doctor.memberEmail}</span>
                            <br>
                            <span>${!doctor.memberKakaoEmail ? "없음" : doctor.memberKakaoEmail}</span>
                            </td>
                            <td class="td-phone" style="width: 10%">${doctor.memberPhone}</td>
                            <td class="td-phone" style="width: 10%">${doctor.doctorLicenseNumber}</td>
                            <td class="td-start" style="width: 10%" >${doctor.createdDate}</td>
                            <td class="td-recent" style="width: 5%; color : ${checkStatus ? "#507cf3" : "#fe657e"}">${memberStatus}</td>
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
        const doctorDetailName = document.getElementById("doctorDetailName");
        const doctorDetailPhone = document.getElementById("doctorDetailPhone");
        const doctorDetailVita = document.getElementById("doctorDetailVita");
        const doctorDetailDatetime = document.getElementById("doctorDetailDatetime");
        const doctorDetailId = document.getElementById("doctorDetailId");
        const doctorDetailEmail = document.getElementById("doctorDetailEmail");
        const doctorDetailStatus = document.getElementById("doctorDetailStatus");
        const doctorDetailSpecialty = document.getElementById("doctorDetailSpecialty");
        const repliesWrap = document.getElementById("doctorDetailReplies");
        const doctorDetailHospital = document.getElementById("doctorDetailHospital");
        const replies = result.replies;
        const memberEmail = result.memberProvider === "KAKAO" ? result.kakaoEmail : result.memberEmail;
        let memberStatus;
        let checkStatus;
        if (result.memberStatus === "INACTIVE" && result.doctorStatus === "INACTIVE") {
            memberStatus = "승인 거절";
            checkStatus = false;
        } else if (result.memberStatus === "ACTIVE" && result.doctorStatus ==="INACTIVE") {
            memberStatus = "승인 대기";
            checkStatus = true;
        } else if (result.memberStatus === "INACTIVE" && result.doctorStatus ==="ACTIVE"){
            memberStatus = "탈퇴";
            checkStatus = false;
        }else{
            memberStatus ="활동 중";
            checkStatus = true;
        }
        doctorDetailStatus.style.color =  checkStatus ? "#507cf3": "#fe657e";
        doctorDetailName.textContent = result.memberName;
        doctorDetailPhone.textContent = result.memberPhone;
        doctorDetailHospital.textContent = result.hospitalName;
        doctorDetailVita.textContent = result.memberVitaAmount;
        doctorDetailDatetime.textContent = result.createdDatetime;
        doctorDetailId.textContent = result.id;
        doctorDetailSpecialty.textContent = result.doctorSpecialty;
        doctorDetailEmail.textContent = memberEmail;
        doctorDetailStatus.textContent = memberStatus;
        // memberDetailProvider.textContent = result.memberProvider;
        if (replies.length === 0) {
            text +=
                `
                <tr><td class="text-light-grey text-center" colspan="4">작성한 답변이 없습니다</td></tr>
                `
        } else {
            replies.forEach((reply) => {
                const check1 = reply.consultationPostStatus === "INACTIVE";
                const check2 = reply.counselReplyStatus === "INACTIVE";
                text += `
            <tr>
                <td>${reply.consultationPostTitle}</td>
                <td style="color :${check1 ? "#507cf3": "#fe657e"}">${check1 ? "Y" : "N"}</td>
                <td style="color :${check2 ? "#507cf3": "#fe657e"}">${check2 ? "Y" : "N"}</td>
                <td>${reply.createdDate}</td>
            </tr>`
            })
        }

        repliesWrap.innerHTML = text;
    }
    return {showDoctors: showDoctors, showDoctorDetail: showDoctorDetail};
})();