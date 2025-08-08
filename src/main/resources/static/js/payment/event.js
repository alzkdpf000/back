// 내용 가져오는 모달안에 버튼
const dateBtn = document.querySelector("ul.select-list.date");
const orderBtn = document.querySelector("ul.select-list.order");
let dataDate = dateBtn.querySelector(".active").dataset.date;
let dataOrder = orderBtn.querySelector(".active").dataset.order;
let dataUnit = dateBtn.querySelector(".active").dataset.unit;

let prevDate, prevOrder, prevUnit;
let dataChangeCheck = false;

let chagneTextDate = "7일";
let chagneTextOrder = "최신 순";
const serverBtn = (btn, check = true) => {
    let dateBtnActive = btn.querySelector(".active");

    btn.addEventListener("click", (e) => {
        const btn = e.target.closest("button.list-btn");
        if (btn) {
            dateBtnActive.classList.remove("active");
            btn.classList.add("active");
            if (check) {
                dataDate !== btn.dataset.date && (dataDate = btn.dataset.date);
                dataUnit !== btn.dataset.unit && (dataUnit = btn.dataset.unit);

                chagneTextDate = btn.firstElementChild.textContent;
            } else {
                dataOrder !== btn.dataset.order &&
                    (dataOrder = btn.dataset.order);
                chagneTextOrder = btn.firstElementChild.textContent;
            }

            dateBtnActive = btn;
        }
    });
};
serverBtn(dateBtn);
serverBtn(orderBtn, false);
const modalLayout = document.querySelector("div.modal");
const modalOpen = document.querySelector("div.date-select-container");
modalOpen.addEventListener("click", (e) => {
    modalLayout.style.display = "flex";
    prevDate = dataDate;
    prevOrder = dataOrder;
    prevUnit = dataUnit;
});

const sendBtn = document.querySelector(".accept-btn");
sendBtn.addEventListener("click", (e) => {
    modalLayout.style.display = "none";
    if (
        prevDate === dataDate &&
        prevOrder === dataOrder &&
        prevUnit === dataUnit
    ) {
        console.log("기존과 같아요");
    } else {
        document.querySelector("span.date").textContent = chagneTextDate;
        document.querySelector("span.date.order").textContent = chagneTextOrder;
        console.log(document.querySelector("span.date.order").textContent);
        console.log(12312);
    }
});
//  모달 내부 동작들//

// 토글 버튼 충전, 결제 내역//
const profilBtns = document.querySelectorAll(".category-text-wrap");
const profilCotentCheck = document.querySelector(".selected-category");

profilBtns.forEach((profilBtn) => {
    profilBtn.addEventListener("click", (e) => {
        // console.log(1);
        const cnt = profilBtn.dataset.cnt;
        console.log(profilCotentCheck);
        profilCotentCheck.style.transform = `translateX(calc(${100 * cnt}% + ${
            16 * cnt
        }px))`;
        // console.log(document.querySelector("span.category-text.active"));
        document
            .querySelector("span.category-text.active")
            .classList.remove("active");

        const spanTag = profilBtn.firstElementChild;
        spanTag.classList.add("active");
    });
});
