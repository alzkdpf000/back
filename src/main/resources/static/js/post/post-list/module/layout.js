const consultationPostListLayout = (() => {

    // ===================== 목록 표시 =====================
    const showList = (posts, keyword = '', selectedCategories = []) => {
        const container = document.getElementById("intersectionObserver");
        if (!container) return;

        if (!posts || posts.length === 0) {
            container.innerHTML = keyword || (selectedCategories && selectedCategories.length > 0)
                ? `<div class="no-search-result-wrap">
                       <img src="https://media.a-ha.io/aha-qna/images/v3/product/feed/message.webp" width="48" height="48" style="color:transparent">
                       <span class="no-search-result-text">검색 결과가 없어요. 다시 검색해 보세요!</span>
                   </div>`
                : "<li>상담글이 없습니다.</li>";
            return;
        }

        let html = '';
        posts.forEach(post => {
            let imgText = '';
            if (post.consultationPostFiles?.length > 0) {
                post.consultationPostFiles.forEach(file => {
                    imgText += `<li>
                                    <img src="/api/files/display?filePath=${file.filePath}&fileName=${file.fileName}" width="161" height="161" alt="">
                                </li>`;
                });
            }

            let providerImgSrc = 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp';
            if (post.memberProvider === "kakao") {
                providerImgSrc = post.memberFilePath?.trim() || providerImgSrc;
            } else if (post.memberFilePath?.trim()) {
                const arr = post.memberFilePath.split('/');
                const fileName = arr.pop();
                const filePath = arr.join('/');
                providerImgSrc = `/api/files/display?filePath=${filePath}&fileName=${fileName}`;
            }

            html += `<li>
                        <a href="/post/detail/${post.id}">
                            <div class="answer-list-container">
                                <img src="${providerImgSrc}" width="48" height="48" class="answer-client-img">
                                <div class="answer-list-content-tag">
                                    <div class="answer-writer-cotent-wrap">
                                        <div class="category-wrap">
                                            <span>${post.categories?.length > 0
                                            ? post.categories[0] + (post.categories.length > 1 ? ` 외 ${post.categories.length - 1}개` : '')
                                            : '기타'}</span>
                                        </div>
                                        <div class="category-bottom-wrap">
                                            <span class="writer-name">${post.memberName}</span>
                                            <svg fill="none" height="3" viewBox="0 0 2 3" width="2" xmlns="http://www.w3.org/2000/svg">
                                                <circle cx="1" cy="1.5" fill="#282b2f" r="1"></circle>
                                            </svg>
                                            <span class="write-create-time write-date">${post.relativeDate || ''}</span>
                                        </div>
                                    </div>
                                    <div class="answer-content-container">
                                        <span class="post-title">${post.consultationPostTitle}</span>
                                        <span class="post-content">${post.consultationPostContent}</span>
                                    </div>
                                    <ul class="answer-imag-container">${imgText}</ul>
                                    <div class="answer-cnt-wrap">
                                        <div class="answer-icon-cnt-wrap">
                                            <span class="strong">${post.consultationPostAnswerCount || 0}명</span>의 의사가 답변했어요
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                     </li>`;
        });

        container.innerHTML = html;
    };

    // ===================== 페이징 표시 =====================
    const showPaging = (criteria, callback, keyword = '', selectedCategories = [], orderType = 'latest') => {
        const pageContainer = document.getElementById("page-container");
        if (!pageContainer) return;

        let html = "";
        if (criteria.startPage > 1) html += `<a href="#" data-page="${criteria.startPage - 1}">이전</a>`;
        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            html += `<a href="#" data-page="${i}">${i}</a>`;
        }
        if (criteria.endPage < criteria.realEnd) html += `<a href="#" data-page="${criteria.endPage + 1}">다음</a>`;

        pageContainer.innerHTML = html;

        pageContainer.querySelectorAll("a").forEach(link => {
            link.addEventListener("click", e => {
                e.preventDefault();
                const pageNum = Number(link.dataset.page);
                callback(pageNum, keyword, selectedCategories, orderType);
            });
        });
    };

    // ===================== 데이터 로드 =====================
    const loadConsultationPosts = async (page = 1, keyword = '', selectedCategories = [], orderType = 'latest') => {
        try {
            const params = new URLSearchParams();
            params.append('keyword', keyword);
            selectedCategories.forEach(cat => params.append('categories', cat));
            params.append('order', orderType);

            const url = `/api/posts/list/${page}${params.toString() ? '?' + params.toString() : ''}`;
            const res = await fetch(url);
            if (!res.ok) throw new Error("상담글 로드 실패");

            const data = await res.json();
            showList(data.consultationPosts, keyword, selectedCategories);

            if (data.consultationPosts && data.consultationPosts.length > 0) {
                showPaging(data.criteria, loadConsultationPosts, keyword, selectedCategories, orderType);
            } else {
                const pageContainer = document.getElementById("page-container");
                if (pageContainer) pageContainer.innerHTML = "";
            }
        } catch (err) {
            console.error(err);
            const container = document.getElementById("intersectionObserver");
            if (container) container.innerHTML = "<li>상담글을 불러오는데 실패했습니다.</li>";
        }
    };

    return {
        showList: showList,
        showPaging: showPaging,
        loadConsultationPosts: loadConsultationPosts,
    };
})();

window.consultationPostListLayout = consultationPostListLayout;
