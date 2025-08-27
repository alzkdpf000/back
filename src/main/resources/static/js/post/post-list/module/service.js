const consultationPostListService = (() => {
    // 현재 선택된 검색 조건
    let keyword = '';
    let categories = [];

    // 상담글 조회
    const getConsultationPost = async (page = 1, search = {}, callback) => {
        search.categories = Array.isArray(search.categories) ? search.categories : [];
        search.keyword = search.keyword || '';

        const params = new URLSearchParams();
        search.categories.forEach(cat => params.append("categories", cat));
        if (search.keyword) params.append("keyword", search.keyword);

        const query = params.toString();
        const response = await fetch(`/api/posts/list/${page}${query ? '?' + query : ''}`);
        if (!response.ok) throw new Error("상담글 로드 실패");

        const result = await response.json();
        if (callback) callback(result);
        return result;
    };

    // 외부에서 호출하는 메서드
    const loadConsultationPosts = async (page = 1, kw = null, catList = null) => {
        if (kw !== null) keyword = kw;
        if (catList !== null) categories = catList;

        return await getConsultationPost(page);
    };

    return { loadConsultationPosts: loadConsultationPosts };
})();