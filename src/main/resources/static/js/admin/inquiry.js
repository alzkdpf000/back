const menubtn1 = document.getElementById("menubtn1");
const sublist1 = document.getElementById("sublist1");
const menubtn2 = document.getElementById("menubtn2");
const sublist2 = document.getElementById("sublist2");
const menubtn3 = document.getElementById("menubtn3");
const sublist3 = document.getElementById("sublist3");
const menubtn4 = document.getElementById("menubtn4");
const sublist4 = document.getElementById("sublist4");
const submenus = document.querySelectorAll(".boot-link");
const btnfilterstatus = document.getElementById("btn-filter-status");
const allchecked1 = document.getElementById("allchecked1");
const allflasechecked1 = document.getElementById("allflasechecked1");
const allchecked2 = document.getElementById("allchecked2");
const allflasechecked2 = document.getElementById("allflasechecked2");
const checkboxactive1 = document.getElementById("checkboxactive1");
const checkboxactive2 = document.getElementById("checkboxactive2");
const bootpopbtn1 = document.getElementById("btn-filter-pm");
const popmenubt1 = document.getElementById("pop-menu-bt1");
const popmenubt2 = document.getElementById("pop-menu-bt2");
const modalclose = document.getElementById("close");
const body = document.getElementById("body");
const modal = document.getElementById("modal");
const modalopen = document.getElementById("modal-open");
const usermenubtn = document.getElementById("usermenubtn");
const usermenu = document.getElementById("usermenu");

// 메뉴 버튼 이벤트
if (menubtn1) {
    menubtn1.addEventListener("click", (e) => {
        sublist1?.classList.toggle("show");
        e.preventDefault();
    });
}
if (menubtn2) {
    menubtn2.addEventListener("click", (e) => {
        sublist2?.classList.toggle("show");
        e.preventDefault();
    });
}
if (menubtn3) {
    menubtn3.addEventListener("click", (e) => {
        sublist3?.classList.toggle("show");
        e.preventDefault();
    });
}
if (menubtn4) {
    menubtn4.addEventListener("click", (e) => {
        sublist4?.classList.toggle("show");
        e.preventDefault();
    });
}

// 유저 메뉴 버튼
if (usermenubtn && usermenu) {
    usermenubtn.addEventListener("click", (e) => {
        usermenu.classList.toggle("show");
    });
}

// 서브메뉴 active 토글
submenus.forEach((submenu) => {
    submenu.addEventListener("click", (e) => {
        e.preventDefault();
        submenus.forEach((active) => active.classList.remove("active"));
        submenu.classList.add("active");
    });
});

// 체크박스 토글
if (checkboxactive1) {
    checkboxactive1.addEventListener("click", () => {
        checkboxactive1.classList.toggle("active");
    });
}
if (checkboxactive2) {
    checkboxactive2.addEventListener("click", () => {
        checkboxactive2.classList.toggle("active");
    });
}

// 전체 선택 / 해제
if (allchecked1) {
    allchecked1.addEventListener("click", () => {
        checkboxactive1?.classList.add("active");
        checkboxactive2?.classList.add("active");
    });
}
if (allflasechecked1) {
    allflasechecked1.addEventListener("click", () => {
        checkboxactive1?.classList.remove("active");
        checkboxactive2?.classList.remove("active");
    });
}

// 필터 팝업 토글
if (btnfilterstatus && popmenubt2) {
    btnfilterstatus.addEventListener("click", () => {
        popmenubt2.classList.toggle("show");
    });
}

// 모달 열기/닫기
if (modalopen && modal && body) {
    modalopen.addEventListener("click", () => {
        modal.classList.add("show");
        modal.style.display = "block";
        body.classList.add("modal-open");
    });
}

if (modalclose && modal && body) {
    modalclose.addEventListener("click", () => {
        modal.classList.remove("show");
        modal.style.display = "none";
        body.classList.remove("modal-open");
    });
}
