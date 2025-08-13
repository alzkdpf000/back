// ########################### 게시글 목록 ###########################
const doctorListContainer = document.querySelector(".content-list"); // 클래스 선택
let text = ``;

doctorLists.forEach((doctor) => {
    text += `
        <li>
            <div class="content-info">
                <div class="content-info-text">
                    <span class="doctorinfo-name">${doctor.memberName}</span>
                    <span class="doctorinfo-specialty">${doctor.doctorSpecialty}</span>
                    <div class="hospital-info">
                        <span class="hospital-name">${doctor.hospitalName}</span>
                        <span class="hospital-address">${doctor.hospitalRoadAddress} ${doctor.hospitalDetailAddress}</span>
                        <span class="hospital-phone">${doctor.hospitalPhone}</span>
                    </div>
                </div>
            </div>
        </li>
    `;
});

doctorListContainer.innerHTML = text; // 변수명 수정

// ########################### 게시글 페이징 ###########################
const pageContainer = document.getElementById("page-container");
text = ``;

if(criteria.hasPreviousPage){
    text += `<a href="/doctor/list/${criteria.startPage - 1}">이전</a>`;
}

for(let i = criteria.startPage; i <= criteria.endPage; i++){
    text += `<a href="/doctor/list/${i}">${i}</a>`;
}

if(criteria.hasNextPage){
    text += `<a href="/doctor/list/${criteria.endPage + 1}">다음</a>`;
}

pageContainer.innerHTML = text;