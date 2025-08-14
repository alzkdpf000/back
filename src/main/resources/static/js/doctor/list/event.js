const pageContainer = document.getElementById("page-container");
text = ``;

if(criteria.hasPreviousPage){
    text += `<a href="/doctor/list/${criteria.startPage - 1}">이전</a>`;
}

for(let i = criteria.startPage; i <= criteria.endPage; i++){
    text += `<a href="/doctor/list/${i}">${i}</a>`;
}

if(criteria.hasNextPage){
    text += `<a href="/doctor/list/${criteria.endPage + 1}">다음</a>`;
}

pageContainer.innerHTML = text;