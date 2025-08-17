// 일반회원 상세 모달 창 열고 닫는 이벤트
const approvalModal = document.querySelector(".approval-modal");
const closeApprovalButton = document.getElementById("approval-modal-close");


closeApprovalButton.addEventListener("click", (e) => {
    approvalModal.classList.remove("show");
    document.body.classList.remove("modal-open");

    setTimeout(() => {
        approvalModal.style.display = "none";
    }, 100);
});


const showPendingDoctors = async (page = 1) => {
    loading.style.display = "block";
    await approvalService.getPendingDoctors(approvalLayout.showPendingDoctors, page)
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)
}


// // 승인 여부
// const acceptBtn = document.querySelectorAll(".accept");
// const refuseBtn = document.querySelectorAll(".refuse");
//
// // 승인 버튼
// acceptBtn.forEach((btn) => {
//     btn.addEventListener("click", (e) => {
//         const tr = e.currentTarget.closest("tr");
//         const status = tr.querySelector(".approval-status");
//         status.textContent = "승인 완료";
//         status.style.color = "green";
//     });
// });
//
// // 거절 버튼
// refuseBtn.forEach((btn) => {
//     btn.addEventListener("click", (e) => {
//         console.log(222);
//         const tr = e.currentTarget.closest("tr");
//         const status = tr.querySelector(".approval-status");
//         status.textContent = "승인 거절";
//         status.style.color = "red";
//     });
// });
