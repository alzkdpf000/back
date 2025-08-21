const paymentService = (() => {
    const getPayments = async (callback, page = 1, keyword = "", categories = []) => {
        console.log(page)
        try {
            const response = await fetch(`/api/admin/payments`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({keyword: keyword, page: page, categories: categories})
            })
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("결제 잘나옴")
                setTimeout(()=>{
                    callback(result);
                },500);

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

    return {getPayments: getPayments}
})();