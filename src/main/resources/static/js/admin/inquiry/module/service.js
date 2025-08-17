const adminService = (()=>{
    const showInquiries = async ()=>{
        try{
            const response = await fetch("/api/admin/inquires")
            const result = await  response.json();
            if(response.ok){
                console.log("문의글 잘나옴")
            }else if(response.status === 409){
                console.log("문의글을 못 찾았어");
            }else{
                const errorText = await response.text();
                console.log(response.status);
                console.log(errorText || "Fetch Error");
            }

            if(callback){
                callback(result);
            }


        }catch (error){
            console.log(error);
        }
    }
    return {showInquiries: showInquiries}
})();