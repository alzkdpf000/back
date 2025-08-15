// 페이지 번호 클릭 이벤트(데이터를 받아와야 하는 곳이라 주석 처리)
const pagiNation = document.querySelector(".pagination.bootpay-pagination");

const showNotices = async (page = 1) => {
    await noticeService.getList(noticeLayout.showList,page)
}
pagiNation.addEventListener("click",async (e)=>{
    if(e.target.classList.contains("paging")){
        e.preventDefault();
        await showNotices(e.target.dataset.page);
    }
})