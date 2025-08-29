const consultationMainPageService = (()=>{
    const getConsultationPost = async (page= 0, callback)=>{

        try{
            const response = await fetch(`/api/${page}`);
            const result = await response.json();


            if(response.ok) {
                console.log("상담글 존재")
                if(callback){
                    setTimeout(() => {
                        callback(result,page);
                    }, 1000)
                }
            }else if(response.status === 404){
                console.log("상담글 없음")
            }else {
                const error = await response.text()
                console.log(error);
            }
            return result;
        }catch (error){
            console.error(error);
        }


    }

    return {getConsultationPost: getConsultationPost}

})();