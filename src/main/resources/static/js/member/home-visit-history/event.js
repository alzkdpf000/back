NodeList.prototype.indexOf = Array.prototype.indexOf;

let check = false;
let modalCheck;
let buttonsCheck = true;

const categories = document.querySelectorAll(".mypage-category-wrapper a");
let temp = document.querySelector("span.active");

categories.forEach((category) => {
    category.addEventListener("click", (e) => {
        e.preventDefault();
        temp.classList.remove("active");
        temp = category.firstElementChild;
        category.firstElementChild.classList.add("active");
    });
});

const shiftHomeVisitRequestUpdatePage = document
    .querySelectorAll(".type-info-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("차트 클릭하여 방문진료글 수정페이지로 이동");
        });
    });

const shiftDoctorInfoPage = document
    .querySelectorAll(".doctor-info-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("의사 정보 클릭하여 의사정보 상세페이지로 이동");
        });
    });

const shiftSendToInboxPage = document
    .querySelectorAll(".doctor-info-content-button-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("이미지 클릭하여 메세지 보내기 창으로 이동");
        });
    });

const shiftHomeVisitRequestPage = document
    .querySelectorAll(".home-visit-history-content-button-wrapper.re-post-btn")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("버튼 클릭하여 방문진료글 등록 페이지로 이동");
        });
    });
