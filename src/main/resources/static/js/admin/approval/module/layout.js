const approvalLayout = (() => {
    const showPendingDoctors = async (result) => {
        let text = ``;

        const doctors = result.doctorsList;
        const criteria = result.criteria;
        const pageWrap = document.querySelector(".pagination.bootpay-pagination.approval-pagination");
        const tbody = document.getElementById("approvalTbody");
        if (doctors.length === 0) {
            text +=
                `
            <td class="text-center font-weight-bold" colspan="5" >대기 중인 의사가 없습니다</td>
            `
        } else {
            doctors.forEach((doctor) => {
                text += `
            <tr>
                                <td class="td-member text-center">
                                    ${doctor.id}
                                </td>
                                <td class="approval-status text-center">${doctor.memberName}</td>
                                <td class="td-role text-center">
                                    ${doctor.doctorLicenseNumber}
                                </td>
                                <td class="td-time text-center">${doctor.createdDate}</td>
                                <td class="td-mng text-center">
                                    <button class="btn btn-light-danger btn-sm edit-btn action-btn" data-doctorid="${dcotor.id}">
                                        <i class="mdi mdi-chevron-right"></i>
                                    </button>
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
    const showPendingDoctor = async (result) => {
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
        const replies = result.replies;
        const memberEmail = result.memberProvider === "KAKAO" ? result.kakaoEmail : result.memberEmail;
        const memberStatus = result.memberStatus === "ACTIVE" ? "활동 중" : "탈퇴";
        doctorDetailStatus.style.color = result.memberStatus === "ACTIVE" ? "#507cf3": "#fe657e";
        doctorDetailName.textContent = result.memberName;
        doctorDetailPhone.textContent = result.memberPhone;
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
    return {showPendingDoctors: showPendingDoctors, showPendingDoctor: showPendingDoctor};
})();