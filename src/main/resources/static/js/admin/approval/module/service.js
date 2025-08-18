const approvalService = (() => {
    const getPendingDoctors = async (callback, page = 1) => {
        console.log(page)
        try {
            const response = await fetch(`/api/admin/doctors/pending?page=${page}`)
            const result = await response.json();
            console.log(result);
            if (response.ok) {
                console.log("승인 대기 의사  잘나옴")
                setTimeout(() => {
                    callback(result);
                }, 1000)
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

    const getPendingDoctor = async (callback, doctorId) => {
        try {
            const response = await fetch(`/api/admin/doctors/pending/${doctorId}`)
            const result = await response.json();

            console.log(result);
            if (response.ok) {
                console.log("의사 잘나옴")
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

    const approve = async (doctorId) => {
        try {
            const response = await fetch(`/api/admin/doctors/${doctorId}/approve`,{
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
            })
            const result = await response.text();

            const responseData = {
                status : response.status,
                msg : result
            }

            console.log(result);
            if (response.ok) {
                console.log("승인 처리 완료")

            } else {
                const errorText = result
                console.log(response.status);
                console.log(errorText || "Fetch Error");
            }
            return responseData;
        } catch (error) {
            console.log("에러입니다.")
            console.log(error);
        }
    }
    const reject = async (doctorId) => {
        try {
            const response = await fetch(`/api/admin/doctors/${doctorId}/reject`,{
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
            })
            const result = await response.text();

            const responseData = {
                status : response.status,
                msg : result
            }
            console.log(result);
            if (response.ok) {
                console.log("거절 처리 완료")
            } else {
                const errorText = result
                console.log(response.status);
                console.log(errorText || "Fetch Error");
            }
            return responseData;
        } catch (error) {
            console.log("에러입니다.")
            console.log(error);
        }

    }
    return {getPendingDoctors: getPendingDoctors, getPendingDoctor: getPendingDoctor, approve: approve, reject: reject}
})();