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
console.log(memberProfileBtn);
memberProfileBtn?.addEventListener("click",(e)=>{
    console.log(123)
    console.log(memberProfileList.classList);
    if(memberProfileList?.classList.contains("show")){
        console.log(1234)
        memberProfileList.classList.remove("show");
    }else{
        console.log(1253)
        memberProfileList.classList.add("show");

    }
      // ?
})

document.addEventListener("click", () => {
    memberProfileList?.classList.remove("show");
});