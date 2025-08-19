

// 결제 상태 선택 토글
let paymentBtnAction = true;
const paySelectBtn = document.getElementById("paymentBtn");
const paySelect = document.getElementById("paymentPopMenu");

paySelectBtn.addEventListener("click", () => {
    paySelect.classList.toggle("show");
});

// 전체 선택  전체 해제
const selectAllBtn = document.getElementById("btn-select-all");
const deselectAllBtn = document.getElementById("btn-deselect-all");
const checkBoxes = document.querySelectorAll(".payment-check-box");
console.log(checkBoxes);
const categorySet = new Set();
let paymentSearch = null;


const showPayments = async (page = 1, keyword = "", categories = []) => {
    if(!paymentBtnAction) return;
    paymentBtnAction = false;
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    let payments = await paymentService.getPayments(paymentLayout.showPayments, page, keyword, categories);

    setTimeout(() => {
        loading.style.display = "none";
        paymentBtnAction = true;
    }, 500)
    return payments;
}



selectAllBtn.addEventListener("click", async (e) => {
    categorySet.add("pending");
    categorySet.add("success");
    categorySet.add("cancel");
    checkBoxes.forEach((box) => {
        const icon = box.querySelector("i.mdi-check");
        console.log(icon);
        console.log(box);
        if (icon) {

            icon.style.display = "inline-block";
            box.classList.add("active");
        }
        box.classList.add("flash");
        box.addEventListener(
            "animationend",
            () => {
                box.classList.remove("flash");
            },
            { once: true }
        );
    });
    selectAllBtn.classList.add("flash");
    selectAllBtn.addEventListener(
        "animationend",
        () => {
            selectAllBtn.classList.remove("flash");
        },
        { once: true }
    );
    selectAllBtn.classList.add("active");
    paymentSearch = await showPayments(1,paymentSearch.search.keyword,[])
});

deselectAllBtn.addEventListener("click", async (e) => {
    categorySet.clear();
    checkBoxes.forEach((box) => {
        const icon = box.querySelector("i.mdi-check");
        if (icon) {
            icon.style.display = "none";
            box.classList.remove("active");
        }
    });
    selectAllBtn.classList.remove("active");
    paymentSearch = await showPayments(1,paymentSearch.search.keyword,[])
});

// 그룹별 상위 체크박스 관련 변수
const pendingBtn=document.getElementById("paymentPending");
const successBtn = document.getElementById("paymentSuccess");
const cancelPaymentBtn = document.getElementById("paymentCancel");

const paymentAction = async (btn,category) =>{

    const icon = btn.querySelector("i.mdi-check");
    console.log(icon)
    console.log(icon.parentElement.parentElement);
    if(categorySet.has(category)){
        icon.style.display = "none";
        icon.parentElement.parentElement.classList.remove("active");
        categorySet.delete(category);
    }else{
        icon.style.display = "inline-block";
        icon.parentElement.parentElement.classList.add("active");
        categorySet.add(category);
    }
    console.log(paymentSearch);
    paymentSearch = await showPayments(1, paymentSearch.search.keyword, Array.from(categorySet));
}
pendingBtn.addEventListener("click",async (e)=>{
    await paymentAction(pendingBtn,"pending");
});

successBtn.addEventListener("click",async (e)=>{
    await paymentAction(successBtn,"success");
});
cancelPaymentBtn.addEventListener("click",async (e)=>{
    await paymentAction(cancelPaymentBtn,"cancel");

});

const paymentKeyword =document.getElementById("paymentKeyword");
const paymentBtn = document.getElementById("paymentKeywordBtn");
paymentKeyword.addEventListener("keydown",async (e)=>{
    if (e.key === "Enter") {
        paymentSearch = await showPayments(1,paymentKeyword.value.trim(),paymentSearch.search.categories);
    }
})

paymentBtn.addEventListener("click", async (e) => {

    paymentSearch = await showPayments(1,paymentKeyword.value.trim(),paymentSearch.search.categories);

})


const paginationPayment = document.querySelector(".payment-pagination");
paginationPayment.addEventListener("click",async (e)=>{
    console.log(paymentSearch.categories);
    paymentSearch = await showPayments(e.target.dataset.page,paymentSearch.search.keyword,paymentSearch.search.categories);
})