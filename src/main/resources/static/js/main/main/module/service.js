const consultationMainPageService = (()=>{
    const getConsultationPost = async (offset= 0, callback)=>{
        const response = await fetch(`/api/${offset}`);
        const consultationPost = await response.json();
        if(callback){
            setTimeout(() => {
                callback(consultationPost);
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
    }

    return {getConsultationPost: getConsultationPost}

})();