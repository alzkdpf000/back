let offset = 0;
const showList = async (offset = 0) => {
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    const consultationMainPage = await consultationMainPageService.getConsultationPost(offset, consultationMainPageLayout.showList);
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)

    return consultationMainPage;
}
showList();

let checkScroll = true;
let consultationMainPage;
let checkMore = true;
window.addEventListener("scroll", async (e) => {
    // 현재 스크롤 위치
    const scrollTop = window.scrollY
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 전체 높이
    const documentHeight = document.documentElement.scrollHeight
    if(scrollTop + windowHeight >= documentHeight - 2) {
        //     바닥에 닿았을 때
        if(checkScroll){
            offset +=5;
            consultationMainPage = await showList(offset);
            checkScroll = false;
        }
        checkMore = consultationMainPage.length === 5;
        setTimeout(() => {
            if(consultationMainPage !== null && checkMore){
                checkScroll = true
            }
        }, 1100);
    }
})