// 사이드바 메뉴 토글
const menuBtns = document.querySelectorAll(".menu-btn");
const allSubMenu = document.querySelector(".menu-sub-list");

document.querySelector(".boot-link.mr-3").addEventListener("click",async (e)=>{
    e.preventDefault();
    inquiryScroll = false;
    allSubMenu.style.display = "none";
    document.querySelectorAll("div.wide-page").forEach((divTag) => {
        divTag.style.display = "none";
    })
    menuBtns.forEach((btn)=>{
        btn.classList.remove("active");
    })
    document.getElementById("notices").style.display="block";
    const contentWrap = document.getElementById("noticeBody");

    contentWrap.innerHTML=`<tr><td class="text-light-grey text-center" colspan="3">조회된 공지가 없습니다.</td></tr>`;
    loading.style.display = "block";
    setTimeout(async () => {
        await showNotices()
        loading.style.display = "none";
    }, 100)

})


menuBtns.forEach((btn) => {

    btn.addEventListener("click", async () => {
        inquiryScroll = false;
        allSubMenu.style.display = "none";
        menuBtns.forEach((b) => b.classList.remove("active"));
        let clickId = btn.classList[btn.classList.length-1];
        console.log(clickId);
        document.querySelectorAll("div.wide-page").forEach((divTag) => {
            console.log(clickId);
            console.log(divTag);
            if(clickId !== "member")
            divTag.style.display = "none";
        })

        if (clickId === "inquiry") {
            document.getElementById(`${clickId}`).style.display = "block";
            page = 1;
            document.getElementById("inquiriesBody").innerHTML = "";
            document.getElementById("countAmount").textContent =0;
            document.getElementById("replyCount").textContent =0;
            document.getElementById("noReplyCount").textContent =0;
            await showList();
            console.log("클릭 이벤트야")
            inquiryScroll = true;
        }else if(clickId==="approval"){
            document.getElementById(`${clickId}`).style.display = "block";
            document.getElementById("approvalTbody").innerHTML = "";
            await showPendingDoctors();
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
