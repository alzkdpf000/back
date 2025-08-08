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
