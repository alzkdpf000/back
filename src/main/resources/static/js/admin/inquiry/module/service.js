const inquiryService = (() => {

    const getInquiries = async (callback, page, keyword, categories, load) => {
        console.log(page)
        try {
            const response = await fetch(`/api/admin/inquires`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({keyword: keyword, page: page, categories: categories})
            })
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("문의글 잘나옴")
                if (callback) {
                    setTimeout(() => {
                        callback(result, load);
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

    const getDetailInquiry = async (callback, inquiryId) => {
        try {
            const response = await fetch(`/api/admin/inquires/${inquiryId}`)
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("문의글 잘나옴")
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
    return {getInquiries: getInquiries, getDetailInquiry: getDetailInquiry}
})();