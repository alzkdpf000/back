const mainService = (() => {

    const getInquiries = async (callback,offset=0,query="",answerStatus="all") => {
        try {
            const response = await fetch(`/api/admin/inquires?offset=${offset}&query=${query}&answerStatus=${answerStatus}`)
            const result = await  response.json();
            console.log(result);
            if(response.ok){
                console.log("문의글 잘나옴")
            if (callback) {
                setTimeout(()=>{
                    callback(result);
                },1000)
            }
            }else{
                const errorText = await response.text();
                console.log(response.status);
                console.log(errorText || "Fetch Error");
            }
            return result.inquiryMemberReplyDTOs;
        } catch (error) {
            console.log(error);
        }
    }
    return {getInquiries: getInquiries}
})();