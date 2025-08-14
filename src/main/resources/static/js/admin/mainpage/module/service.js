const mainService = (() => {

    const getInquiries = async (callback,page,query,answerStatus,load) => {
        try {
            const response = await fetch(`/api/admin/inquires/${page}?query=${query}&answerStatus=${answerStatus}`)
            const result = await  response.json();
            console.log(result);
            if(response.ok){
                console.log("문의글 잘나옴")
            if (callback) {
                setTimeout(()=>{
                    callback(result,load);
                },1000)
            }
            }else{
                const errorText = await response.text();
                console.log(response.status);
                console.log(errorText || "Fetch Error");
            }
            return result;
        } catch (error) {
            console.log(error);
        }
    }

    // const getDetailInquiry = async (callback)
    return {getInquiries: getInquiries}
})();