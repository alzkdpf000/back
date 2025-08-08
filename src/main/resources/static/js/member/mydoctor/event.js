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

const doctorInfoUseToImg = document
    .querySelectorAll(".contentInfoImg")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("프로필 사진 클릭해서 의사정보페이지로 전환");
        });
    });

const doctorInfoUseToName = document
    .querySelectorAll(".doctorinfo-name")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("이름 클릭해서 의사정보페이지로 전환");
        });
    });

const sendInboxButton = document
    .querySelectorAll(".mydoctor-content-button-wrapper.mail-send-btn")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("쪽지보내기");
        });
    });

const favoriteCheckButton = document
    .querySelectorAll(".mydoctor-content-button-wrapper.interest-btn.active")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("관심버튼클릭");
        });
    });
