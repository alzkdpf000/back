const noticeService = (() => {
    const getList = async (callback, page = 1) => {
        try {
            const response = await fetch(`/api/admin/notices/${page}`);
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("공지글 잘나옴")
                if (callback) {
                    setTimeout(() => {
                        callback(result);
                    }, 1000)
                }
            } else {
                const errorText = await response.text();
                console.log(response.status);
                console.log(errorText || "Fetch Error");
            }
            return result;
        } catch (error) {
            console.log(error);
        }
    }
    return {getList:getList}
})();