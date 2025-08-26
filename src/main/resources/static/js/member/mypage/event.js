NodeList.prototype.indexOf = Array.prototype.indexOf;
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

const changeEmailButton = document.getElementById("change-email-button");
changeEmailButton.addEventListener("click", (e) => {
    console.log("눌림");
});

const changePhoneButton = document.getElementById("change-phone-button");
changePhoneButton.addEventListener("click", (e) => {
    console.log("눌림");
});

const changePasswordButton = document.getElementById("change-password-button");
changePasswordButton.addEventListener("click", (e) => {
    console.log("눌림");
});

const connectSMSAccountButton = document.getElementById("connect-sms-button");
connectSMSAccountButton.addEventListener("click", (e) => {
    console.log("눌림");
});

const disconnectSMSAccountButton = document.getElementById(
    "disconnect-sms-button"
);
disconnectSMSAccountButton.addEventListener("click", (e) => {
    console.log("눌림");
});

const logoutButton = document.getElementById("logout-button");
logoutButton.addEventListener("click", (e) => {
    console.log("눌림");
});

const deleteAccountButton = document.getElementById("delete-button");
deleteAccountButton.addEventListener("click", (e) => {
    console.log("눌림");
});

// deleteAccountButton.addEventListener("click", (e) => {});

// let modalCheck;

// const showWarnModal = (modalMessage) => {
//     modalCheck = false;
//     document.getElementById("content-wrap").innerHTML = modalMessage;
//     document.querySelector("div.warn-modal").style.animation = "popUp 0.5s";
//     document.querySelector("div.modal").style.display = "flex";
//     setTimeout(() => {
//         modalCheck = true;
//     }, 500);
// };

// document.querySelector("div.modal").addEventListener("click", (e) => {
//     if (modalCheck) {
//         document.querySelector("div.warn-modal").style.animation =
//             "popDown 0.5s";
//         setTimeout(() => {
//             document.querySelector("div.modal").style.display = "none";
//         }, 450);
//     }
// });

document.addEventListener("DOMContentLoaded", function (){
    // REST API 호출
    fetch("/api/mypage/profile")
        .then(res => res.json())
        .then(profile => {
            console.log(profile)
            if (profile) {
                // 프로필 이미지
                const profileImg = document.querySelector(".mypage-main-profile-body");
                // 파일 경로랑 파일이름이 있으면 프로필 경로에 넣는다.
                if (profile.filePath && profile.fileName){
                    profileImg.src = profile.filePath + profile.fileName;
                //  없으면 기본 프로필 설정
                }else{
                    profileImg.src = profile.kakaoProfileUrl || "images/default-profile.png";
                }

                const memberName = document.querySelector("#memberName");
                const memberEmail = document.querySelector("#memberEmail");
                const memberPhone = document.querySelector("#memberPhone");

                if (memberName) memberName.textContent = profile.memberName || '';
                if (memberEmail) memberEmail.textContent = profile.memberEmail || profile.kakaoEmail || '';
                if (memberPhone) memberPhone.textContent = profile.memberPhone || '';





            }
        })
})


