const noticeLayout =(()=>{
    const showList = async (result)=>{
        const notices = result.noticeSummaryDTOS;
        const pageWrap = document.querySelector(".pagination.bootpay-pagination");
        const contentWrap = document.getElementById("noticeBody");
        const criteria = result.criteria;
        let text= ``;
        notices.forEach((notice)=> {
            text += `<tr>
                <td class="td-date text-grey">${notice.createdDateAndDay}</td>
                <td class="td-title">
                    <a data-id="${notice.id}" class="detail-notice">
                        <span class="notice-title">${notice.noticesTitle}</span>
                    </a>
                </td>
                <td class="td-writer">골든타임</td>
            </tr>`
        })
        notices.length > 0 && (contentWrap.innerHTML = text);
        console.log(criteria);
        text=``;
        if(criteria.hasPreviousPage){
            text = `
            <li class="page-item page-num">
                <a data-page="${criteria.startPage - 1}" class="paging">이전</a>
            </li>`

        }

        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            text += `
            <li class="page-item page-num ${i === criteria.page && "active"}">
                <a data-page="${i}" class="paging">${i}</a>
            </li>
        `;
        }

        if(criteria.hasNextPage){
            text += `
            <li class="page-item page-num">
                <a data-page="${criteria.endPage + 1}" class="paging">다음</a>
            </li>`
        }
        pageWrap.innerHTML=text;
    }
    return {showList: showList}
})();