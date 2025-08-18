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
                let check = doctor.doctorStatus ==="INACTIVE" && doctor.memberStatus === "ACTIVE";
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
                                <td class="text-center" style="color:${check ? "#507cf3":"#fe657e"}">${check ? "승인 대기" : "승인 거절"}</td>
                                <td class="td-mng text-center">
                                    <button class="btn btn-light-danger btn-sm edit-btn action-btn" data-doctorid="${doctor.id}">
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
        const pendingName = document.getElementById("pendingName");
        const pendingHospitalPhone = document.getElementById("pendingHospitalPhone");
        const pendingHospital = document.getElementById("pendingHospital");
        const pendingDetailAddress = document.getElementById("pendingDetailAddress");
        const pendingZipCode = document.getElementById("pendingZipCode");
        const pendingId = document.getElementById("pendingId");
        const pendingEmail = document.getElementById("pendingEmail");
        const pendingRoadAddress = document.getElementById("pendingRoadAddress");
        const pendingDatetime = document.getElementById("pendingDatetime");
        const pendingUrl = document.getElementById("pendingUrl");
        const pendingSpecialty = document.getElementById("pendingSpecialty");
        const pendingLicense = document.getElementById("pendingLicense");
        const memberEmail = result.memberProvider === "KAKAO" ? result.kakaoEmail : result.memberEmail;
        pendingName.textContent = result.memberName;
        pendingRoadAddress.textContent = result.roadAddress;
        pendingDatetime.textContent = result.createdDatetime;
        pendingHospital.textContent = result.hospitalName;
        pendingDetailAddress.textContent = result.detailAddress;
        pendingZipCode.textContent = result.zipCode;
        pendingLicense.textContent = result.doctorLicenseNumber;
        pendingUrl.textContent = result.hospitalHomepageUrl ? result.hospitalHomepageUrl : "존재 안함"  ;
        pendingHospitalPhone.textContent = result.memberPhone;
        pendingId.textContent = result.id;
        pendingSpecialty.textContent = result.doctorSpecialty;
        pendingEmail.textContent = memberEmail;



    }
    return {showPendingDoctors: showPendingDoctors, showPendingDoctor: showPendingDoctor};
})();