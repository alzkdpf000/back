const doctorService = (() => {
    const getDoctors = async (page = 1) => {
        const response = await fetch(`/api/doctor/list/${page}`);
        if (!response.ok) {
            const error = await response.text();
            console.error(error);
            return;
        }
        const data = await response.json();
        return data;
    };

    return { getDoctors };
})();