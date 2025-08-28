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

// 결제
const paymentAccountButton = document.querySelectorAll("button.payment-account-button");
paymentAccountButton.forEach((button) => {
    button.addEventListener("click", (e) => {
       const item = button.dataset.item;
       const price = button.dataset.price;

        pay({item: item, price: price})
    });
})

const pay = async ({item, price}) => {
    try {
        const response = await Bootpay.requestPayment({
            application_id: "68affb6d836e97280fee7f28",
            price: price,
            order_name: "테스트결제",
            order_id: "TEST_ORDER_ID",
            pg: "카카오페이",
            // method: "계좌이체",
            tax_free: 0,
            user: {
                id: "회원아이디",
                username: "회원이름",
                phone: "01000000000",
                email: "test@test.com",
            },
            items: [
                {
                    id: "item_id",
                    name: item,
                    qty: 1,
                    price: price,
                },
            ],
            extra: {
                open_type: "iframe",
                card_quota: "0,2,3",
                escrow: false,
            },
        });
        switch (response.event) {
            case "issued":
                // 가상계좌 입금 완료 처리
                break;
            case "done":
                console.log(response);


                // 결제 완료 처리
                break;
            case "confirm": //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                console.log(response.receipt_id);
                /**
                 * 1. 클라이언트 승인을 하고자 할때
                 * // validationQuantityFromServer(); //예시) 재고확인과 같은 내부 로직을 처리하기 한다.
                 */
                const confirmedData = await Bootpay.confirm(); //결제를 승인한다
                if (confirmedData.event === "done") {
                    //결제 성공
                }

                /**
                 * 2. 서버 승인을 하고자 할때
                 * // requestServerConfirm(); //예시) 서버 승인을 할 수 있도록  API를 호출한다. 서버에서는 재고확인과 로직 검증 후 서버승인을 요청한다.
                 * Bootpay.destroy(); //결제창을 닫는다.
                 */
                break;
        }
    } catch (e) {
        // 결제 진행중 오류 발생
        // e.error_code - 부트페이 오류 코드
        // e.pg_error_code - PG 오류 코드
        // e.message - 오류 내용
        console.log(e.message);
        switch (e.event) {
            case "cancel":
                // 사용자가 결제창을 닫을때 호출
                console.log(e.message);
                break;
            case "error":
                // 결제 승인 중 오류 발생시 호출
                console.log(e.error_code);
                break;
        }
    }
};























