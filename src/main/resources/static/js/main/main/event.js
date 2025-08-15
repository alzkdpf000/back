let checkMore = true;
let page = 1
const showList = async (page =1) => {
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    const consultationMainPage = await consultationMainPageService.getConsultationPost(page,consultationMainPageLayout.showList);
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
    checkMore = consultationMainPage.consultationPosts.length === consultationMainPage.scrollCriteria.rowCount;
    console.log(checkMore)
    return consultationMainPage;
}
showList();

let checkScroll = true;
let consultationMainPage;

window.addEventListener("scroll", async (e) => {
    if(!checkMore) {return;}
    // 현재 스크롤 위치
    const scrollTop = window.scrollY
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 전체 높이
    const documentHeight = document.documentElement.scrollHeight
    if(scrollTop + windowHeight >= documentHeight - 2) {
        //     바닥에 닿았을 때
        if(checkScroll){
            consultationMainPage = await showList(++page);
            checkScroll = false;
        }
        checkMore = consultationMainPage.consultationPostFiles.length === consultationMainPage.scrollCriteria.rowCount
        setTimeout(() => {
            if(consultationMainPage !== null && checkMore){
                checkScroll = true
            }
        }, 1100);
    }
})