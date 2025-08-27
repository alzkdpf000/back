const consultationPostListLayout = (() => {

    const showList = (posts) => {
        const container = document.getElementById("consultation-post-list"); // 기존 HTML ul 또는 div
        if (!container) return;

        let html = '';

        if (!posts || posts.length === 0) {
            container.innerHTML = "<li>상담글이 없습니다.</li>";
            return;
        }

        posts.forEach(post => {
            html += `
                <li>
                    <a href="/post/detail/${post.id}">
                        <div class="answer-list-container">
                            <img src="${post.memberFilePath || 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp'}" 
                                 width="48" height="48" class="answer-client-img">
                            <div class="answer-list-content-tag">
                                <div class="answer-writer-cotent-wrap">
                                    <div class="category-wrap">
                                        <span>${post.categories && post.categories.length > 0 ? post.categories[0] + (post.categories.length > 1 ? ` 외 ${post.categories.length - 1}개` : '') : '전체'}</span>
                                    </div>
                                    <div class="category-bottom-wrap">
                                        <span class="writer-name">${post.memberName}</span>
                                        <svg fill="none" height="3" viewBox="0 0 2 3" width="2" xmlns="http://www.w3.org/2000/svg"><circle cx="1" cy="1.5" fill="#282b2f" r="1"></circle></svg>
                                        <span class="write-create-time write-date">${post.relativeDate || ''}</span>
                                    </div>
                                </div>
                                <div class="answer-content-container">
                                    <span class="post-title">${post.consultationPostTitle}</span>
                                    <span class="post-content">${post.consultationPostContent}</span>
                                </div>
                                <ul class="answer-imag-container">
                                    ${post.consultationPostFiles?.map(file => `
                                        <li><img src="${file.filePath}" width="161" height="161" alt=""></li>
                                    `).join('') || ''}
                                </ul>
                                <div class="answer-cnt-wrap">
                                    <div class="answer-icon-cnt-wrap">
                                        <svg fill="none" height="24" viewBox="0 0 25 24" width="25" xmlns="http://www.w3.org/2000/svg">
                                            <path clip-rule="evenodd" d="M13.1389 1C7.3244 1 2.61081 5.92486 2.61081 12C2.61081 13.7841 3.01814 15.4716 3.74146 16.9639L1.76928 21.3794C1.58199 21.7987 1.65614 22.2947 1.95688 22.6344C2.25762 22.9741 2.72517 23.0899 3.13986 22.9274L7.43263 21.2455C9.07704 22.3554 11.0373 23 13.1389 23C18.9534 23 23.667 18.0751 23.667 12C23.667 5.92486 18.9534 1 13.1389 1Z" fill="#2756EC" fill-rule="evenodd"></path>
                                            <path clip-rule="evenodd" d="M9.66699 15H11.6306L11.9761 13.9271H14.3124L14.667 15H16.667L14.2943 8.71817C14.1215 8.25093 13.6397 8 13.1761 8C12.6852 8 12.1852 8.23362 12.0034 8.71817L9.66699 15ZM13.1488 10.3103L13.8397 12.4648H12.4488L13.1488 10.3103Z" fill="white" fill-rule="evenodd"></path>
                                        </svg>
                                        <span class="doctor-cnt"> 
                                            <span class="strong">${post.consultationPostAnswerCount || 0}명</span>의 의사가 답변했어요
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
            `;
        });

        container.innerHTML = html;
        container.appendChild(fragment);
    };

    const showPaging = (criteria) => {
        const pageContainer = document.getElementById("page-container");
        if (!pageContainer) return;

        let html = "";
        if (criteria.hasPreviousPage) html += `<a href="#" data-page="${criteria.startPage - 1}">이전</a>`;
        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            html += `<a href="#" data-page="${i}">${i}</a>`;
        }
        if (criteria.hasNextPage) html += `<a href="#" data-page="${criteria.endPage + 1}">다음</a>`;

        pageContainer.innerHTML = html;

        pageContainer.querySelectorAll("a").forEach(link => {
            link.addEventListener("click", e => {
                e.preventDefault();
                const page = Number(link.dataset.page);
                const keyword = document.getElementById("keywordInput")?.value || '';
                consultationPostListEvent.loadConsultationPosts(page, { keyword, categories: consultationPostListEvent.categoryList });
            });
        });
    };

    return { showList: showList, showPaging: showPaging };
})();