// 페이지 번호 클릭 이벤트(데이터를 받아와야 하는 곳이라 주석 처리)
const pagiNation = document.querySelector(".pagination.bootpay-pagination");

const showNotices = async (page = 1) => {
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    const notices = await noticeService.getList(noticeLayout.showList,page)
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
}
pagiNation.addEventListener("click",(e)=>{
    if(e.target.classList.contains("paging")){
        e.preventDefault();
        loading.style.display = "block";
        noticeService.getList(noticeLayout.showList,e.target.dataset.page);
        setTimeout(() => {
            loading.style.display = "none";
        }, 1000)
    }
})