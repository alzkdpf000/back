let checkMore = true;
let consultationMainPage = null;
let checkScroll = false;
const showList = async (page = 1) => {
    const loading = document.getElementById("loading");
    loading.style.display = "block";
    consultationMainPage = await consultationMainPageService.getConsultationPost(page, consultationMainPageLayout.showList);
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
    checkMore = consultationMainPage.consultationPosts.length === 7;
}

document.addEventListener("DOMContentLoaded", async () => {
    window.scrollTo(0, 0);
    await showList();
    checkScroll =true;
    console.log(consultationMainPage);
});



window.addEventListener("scroll", async (e) => {

    if (!checkMore) {
        return;
    }
    if(!checkScroll){
        return;
    }
    // 현재 스크롤 위치
    const scrollTop = window.scrollY
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 전체 높이
    const documentHeight = document.documentElement.scrollHeight
    if (scrollTop + windowHeight >= documentHeight - 2) {
        //     바닥에 닿았을 때
        if (checkScroll && consultationMainPage) {
            checkScroll= false;
            console.log("몇 번 실행되니")
            console.log(consultationMainPage);
            consultationMainPage = await showList(consultationMainPage.scrollCriteria.page + 1);
            console.log(consultationMainPage);
            checkMore = consultationMainPage?.consultationPosts.length === 7;
        }
        setTimeout(() => {
            if (consultationMainPage !== null && checkMore) {
                checkScroll = true
                console.log("지금 실행")
            }
        }, 1100);
    }
})