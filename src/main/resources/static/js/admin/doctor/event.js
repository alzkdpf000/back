let doctorResponse = null;

const doctorKeywordInput = document.getElementById("doctorKeyword");
const doctorKeywordBtn = document.getElementById("doctorKeywordBtn");


const showDoctors = async (page = 1, keyword = "") => {
    doctorResponse = await doctorService.getDoctors(doctorLayout.showDoctors, page, keyword)
    doctorResponse = doctorResponse.search;
}


doctorKeywordInput.addEventListener("keydown", async (e) => {
    if (e.key === "Enter") {
        await showDoctors(1, doctorKeywordInput.value.trim());
    }
})
doctorKeywordBtn.addEventListener("click", async (e) => {
    await showDoctors(1, doctorKeywordInput.value.trim());
})


// 페이지 번호 클릭 이벤트(데이터를 받아와야 하는 곳이라 주석 처리)
const paginationDoctor = document.querySelector(".pagination.bootpay-pagination.doctor-pagination");

paginationDoctor.addEventListener("click", async (e) => {
    if (e.target.classList.contains("paging")) {
        e.preventDefault();
        await showDoctors(e.target.dataset.page, doctorResponse.keyword);
    }
})


doctorMenuBtn.addEventListener("click", async (e) => {
    e.preventDefault();
    document.querySelectorAll("div.wide-page").forEach((divTag) => {
        divTag.style.display = "none";
    })
    document.getElementById("doctors").style.display = "block";
    doctorMenuBtn.classList.add("checked");
    memberTab.classList.remove("active");
    loading.style.display = "block";
    await showDoctors();
    setTimeout(async () => {
        loading.style.display = "none";
    }, 100)
    memberMenuBtn.classList.remove("checked");
    doctorMenuBtn.classList.add("checked");
    memberMenuBtn.classList.remove("checked");
});


const doctorMoadl = document.getElementById("doctorModal");
const closeButtonDoctorModal = document.getElementById("doctorModalClose");


closeButtonDoctorModal.addEventListener("click", (e) => {
    doctorMoadl.classList.remove("show");
    document.body.classList.remove("modal-open");
    setTimeout(() => {
        doctorMoadl.style.display = "none";
    }, 100);
});


const doctorsTbody = document.getElementById("doctorsTbody");
doctorsTbody.addEventListener("click", async (e) => {
    const memberDetailBtn = e.target.closest(".member-detail-btn");
    if (memberDetailBtn) {
        const doctorId = memberDetailBtn.dataset.doctorid;
        loading.style.display = "block";
        await doctorService.getDetailDoctor(doctorLayout.showDoctorDetail, doctorId);
        // await memberService.getDetailMember(memberLayout.showMemberDetail, memberId);
        setTimeout(() => {
            loading.style.display = "none";
            doctorMoadl.classList.add("show");
            doctorMoadl.style.display = "block";
        }, 1000)
    }
});

const memberTab = document.getElementById("memberTab");
const doctorTab = document.getElementById("doctorTab");
const memberBody = document.getElementById("members");
const doctorBody = document.getElementById("doctors");
memberTab.addEventListener("click", async (e) => {
    console.log("멤버 탭을 선택햇아여")
    doctorBody.style.display = "none";
    memberTab.classList.add("active");
    loading.style.display = "block";
    await showMembers();
    setTimeout(async () => {
        loading.style.display = "none";
    }, 1000)
    doctorTab.classList.remove("active");
    doctorMenuBtn.classList.remove("checked");
    memberMenuBtn.classList.add("checked");
    memberBody.style.display = "block";
});

doctorTab.addEventListener("click", async (e) => {
    memberBody.style.display = "none";
    doctorTab.classList.add("active");
    loading.style.display = "block";
    await showDoctors();
    setTimeout(async () => {
        loading.style.display = "none";
    }, 1000)
    memberTab.classList.remove("active");
    doctorMenuBtn.classList.add("checked");
    memberMenuBtn.classList.remove("checked");
    doctorBody.style.display = "block";
});



