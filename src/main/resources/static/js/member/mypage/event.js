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
            console.log(1234)
            if (profile) {
                // 프로필 이미지
                const profileImg = document.querySelector(".mypage-main-profile-body");
                console.log(profileImg.src)
                // 파일 경로랑 파일이름이 있으면 프로필 경로에 넣는다.
                if (profile.provider === "kakao" && profile.kakaoProfileUrl){
                    profileImg.src = profile.kakaoProfileUrl;
                }else if (profile.filePath && profile.fileName){
                    profileImg.src = `/api/files/display?filePath=${profile.filePath}&fileName=${profile.fileName}`;
                //  없으면 기본 프로필 설정
                }

                const memberName = document.querySelector("#memberName");
                const memberEmail = document.querySelector("#memberEmail");
                const memberPhone = document.querySelector("#memberPhone");

                if (memberName) memberName.textContent = profile.memberName || '';
                if (memberEmail) memberEmail.textContent = profile.memberEmail || profile.kakaoEmail || '';
                if (memberPhone) memberPhone.textContent = profile.memberPhone || '';

            }
        })
        .catch(error => {
            console.error("프로필 정보를 불러오는 중 오류 발생: ", error);
        });
});

// 프로필 이미지 수정
const profileImg = document.querySelector(".mypage-main-profile-body");
// const fileInput = document.querySelector("input");
const confirmBtn = document.querySelector(".confirm-profile-btn");
const fileInput = document.getElementById("profileFileInput");

fileInput.type = "file";
fileInput.accept = "image/*";

profileImg.addEventListener("click", () => {
    console.log(1112);
   fileInput.click();
});

confirmBtn.addEventListener("click",() => {
    const file = fileInput.files[0];
    if (!file) return alert("이미지 선택");

    const formData = new FormData();
    formData.append("file", file);

    fetch("api/mypage/profile/upload", {
        method: "POST",
        body: formData
    })
        .then(res => res.json())
        .then(data => {
            const filePath = fileInfo.filePath;
            const fileName = fileInfo.fileName;
            profileImg.src = `/api/files/display?filePath=${filePath}&fileName=${fileName}`
            alert('프로필이 변경되었습니다.')
        })
        .catch(err => console.error("프로필 업로드 오류: ", err));
});

const profileImage = document.getElementById("profileFileInput");
profileImage.addEventListener("change", (e) => {
    // console.log(e.target.files);
    const [file] = e.target.files;
    if(!file.type.includes("image")){
        alert("이미지 파일만 업로드할 수 있습니다.")
        return;
    }
    const reader = new FileReader();
    const thumbnail = document.querySelector("img.mypage-main-profile-body")
    reader.readAsDataURL(file);
    reader.addEventListener("load", (e) => {
        // console.log(e.target.result);
        const path = e.target.result;
        thumbnail.src = path;
    });
});

