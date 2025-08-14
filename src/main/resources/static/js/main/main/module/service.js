const consultationMainPageService = (()=>{
    const getConsultationPost = async (limit,offset= 0, callback)=>{

        try{
            const response = await fetch(`/api/${offset}?limit=${limit}`);
            const consultationPost = await response.json();
            if(callback){
                setTimeout(() => {
                    callback(limit,consultationPost);
                }, 1000)
            }

            if(response.ok) {
                console.log("상담글 존재")
            }else if(response.status === 404){
                console.log("상담글 없음")
            }else {
                const error = await response.text()
                console.log(error);
            }

            return consultationPost;
        }catch (error){
            console.error(error);
        }


    }

    return {getConsultationPost: getConsultationPost}

})();