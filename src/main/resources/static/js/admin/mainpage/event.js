// 사이드바 메뉴 토글
const menuBtns = document.querySelectorAll(".menu-btn");
const allSubMenus = document.querySelectorAll(".menu-sub-list");

let checkMore = true;
let inquires;
let inquiryScroll = false;
let page = 1;
const showList = async (page = 1, query = "", answerStatus = "all", load = false) => {
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    const inquiresList = await inquiryService.getInquiries(inquiryLayout.showInquiries, page, query, answerStatus, load);

    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
    checkMore = inquiresList.inquiryMemberReplyDTOs.length === inquiresList.scrollCriteria.rowCount;
    return inquiresList;
}




menuBtns.forEach((btn) => {
    btn.addEventListener("click", async () => {
        allSubMenus.forEach((submenu) => (submenu.style.display = "none"));
        menuBtns.forEach((b) => b.classList.remove("active"));

        let clickId = btn.classList[2];
        console.log(clickId);
        document.querySelectorAll("div.wide-page").forEach((divTag) => {
            divTag.style.display = "none";
        })
        document.getElementById(`${clickId}`).style.display = "block";
        if (clickId === "inquiry") {
            page = 1;
            document.getElementById("inquiriesBody").innerHTML = "";
            await showList();
            console.log("클릭 이벤트야")
            inquiryScroll = true;
        }
        btn.classList.add("active");


        const targetId = btn.getAttribute("aria-controls");
        const targetMenu = document.getElementById(targetId);
        if (targetMenu) targetMenu.style.display = "block";
    });
});


const icons = document.querySelectorAll(".icon-wrapper i");

// 상단 오른쪽 관리자 이메일 클릭 시 리스트 출력
// 출력된 리스트 다시 닫기
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");

userMenuWrapper.addEventListener("click", (e) => {
    e.preventDefault();
    if (userMenuContent.classList.contains("show")) {
        userMenuContent.classList.remove("show");
    } else {
        userMenuContent.classList.add("show");
    }
});

document.addEventListener("click", (e) => {
    e.preventDefault();
    if (
        // userMenuContent 안넣어주면 안에 걸 눌러도 리스트가 닫힌다.
        !userMenuWrapper.contains(e.target) &&
        !userMenuContent.contains(e.target)
    ) {
        userMenuContent.classList.remove("show");
    }
});
