const memberService = (() => {
    const getMembers = async (callback, page = 1,keyword="") => {
        console.log(page)
        try {
            const response = await fetch(`/api/admin/members`,{
                method:"POST",
                headers:{
                    "Content-Type": "application/json"
                },
                body : JSON.stringify({keyword: keyword, page: page})
            })
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("사용자 잘나옴")
                callback(result);
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

    const getDetailMember = async (callback, memberId) => {
        try {
            console.log(memberId)
            const response = await fetch(`/api/admin/members/${memberId}`)
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("사용자 잘나옴")
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
            console.log("에러입니다.")
            console.log(error);
        }
    }
    return {getMembers: getMembers, getDetailMember: getDetailMember}
})();