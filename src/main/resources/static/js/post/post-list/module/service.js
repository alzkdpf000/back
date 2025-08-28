const consultationPostListService = (() => {
    // 현재 선택된 검색 조건
    let keyword = '';
    let categories = [];

    const loadConsultationPosts = async (page = 1, keyword = '', selectedCategories = [], orderType = 'latest') => {
        try {
            const params = new URLSearchParams();
            params.append('keyword', keyword);
            selectedCategories.forEach(cat => params.append('categories', cat));
            params.append('order', orderType); // 정렬 조건 추가

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

    return { loadConsultationPosts: loadConsultationPosts };
})();