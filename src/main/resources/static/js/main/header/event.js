const header = document.getElementById('header');

let lastScrollY = 0;

window.addEventListener('scroll', () => {
    if (window.scrollY > 5) {
        header.classList.add('show');
    } else {
        header.classList.remove('show');
    }
});
const memberProfileBtn= document.querySelector("button.main-header-login-profile-icon-wrapper-profile");
const memberProfileList = document.querySelector("ul.member-profile-btn-modal");
memberProfileBtn?.addEventListener("click",(e)=>{
    memberProfileList.classList.contains("show") ? memberProfileList.classList.remove("show") :  memberProfileList.classList.add("show");
})

document.getElementById(`${category}`).classList.add("active");
