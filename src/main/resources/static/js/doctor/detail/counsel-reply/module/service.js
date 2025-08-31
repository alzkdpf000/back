const doctorService = (() => {
    const getDoctorDetail = async (doctorId, page) => {
        const res = await fetch(`/api/doctors/detail/${doctorId}/${page}`);
        if (!res.ok) throw new Error("의사 상세 정보 불러오기 실패");
        return await res.json();
    };

    return { getDoctorDetail: getDoctorDetail };
})();