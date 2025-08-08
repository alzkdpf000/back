HTMLCollection.prototype.forEach = Array.prototype.forEach;
let modalCheck;
//  광클 예방
let buttonsCheck = true;
// 관심 추가 버튼
const buttons = document.querySelectorAll("button.interest-btn");

//숨겨진 input
const hiddenIuput = document.querySelector(".hidden-input");

const listLayout = document.getElementById("intersectionObserver");

// 체크박스 영역 부분
const label = document.querySelector(".checkbox-label");
// 체크시 이미지 부분
const svg = document.querySelector(".checkbox-svg");
// 쪽지 보내기 버튼
const sendBtn = document.querySelector(".mail-send-btn-check");

const textWarnCheck = (tag, check = false) => {
    let count = tag.value.length;
    const temp = check
        ? document.querySelector(".content-text-warn.text-warn")
        : document.querySelector(".mail-title-warn.text-warn");
    temp.setAttribute("data-count", count);
    // console.log(111);
};

/**
 * 강사님 모달 코드 부분 응용한 곳
 */
let check = false;

const showMailModal = () => {
    modalCheck = false;
    document.querySelector("div.warn-modal").style.animation = "popUp 0.5s";
    document.querySelector("div.modal").style.display = "flex";
    setTimeout(() => {
        modalCheck = true;
    }, 500);
};

const showWarnModal = (modalMessage) => {
    modalCheck = false;
    document.getElementById("content-wrap").innerHTML = modalMessage;
    document.querySelector("div.warn-modal").style.animation = "popUp 0.5s";
    document.querySelector("div.modal").style.display = "flex";
    setTimeout(() => {
        modalCheck = true;
    }, 500);
};

document.querySelector("div.modal").addEventListener("click", (e) => {
    if (modalCheck) {
        document.querySelector("div.warn-modal").style.animation =
            "popDown 0.5s";
        setTimeout(() => {
            document.querySelector("div.modal").style.display = "none";
        }, 450);
    }
});

/********************************************************************************/

// 무한 스크롤리여서 이벤트를 동적 처리를 위한 부분
listLayout.addEventListener("click", (e) => {
    const mailBtn = e.target.closest(".mail-send-btn");
    const interestBtn = e.target.closest(".interest-btn");
    const divTag = e.target.closest(".content-info");
    const heartImg = divTag.querySelector(".docterinfo-favorite-wrapper");
    const people = heartImg?.querySelector(".doctorinfo-favoriteCount");
    const addressKakao = e.target.closest(".hospital-info");
    // 모달 전에 쪽지 보내기 누르면 모달이 보이게 하기
    if (mailBtn) {
        e.stopPropagation();
        e.preventDefault();
        mailSendModal.style.display = "flex";
        doctorSearchInput.value = "의사이름";
        doctorRemoveBtn.style.display = "block";
        hiddenIuput.value = doctorSearchInput.value;
        doctorSearchInput.disabled = true;
    } else if (interestBtn) {
        // 관심 형태에 따라 클릭 버튼 다르게
        e.stopPropagation();
        e.preventDefault();
        let message = ``;
        if (!buttonsCheck) {
            return;
        }
        buttonsCheck = false;

        if (interestBtn.classList[2] === "active") {
            console.log(e.target);
            interestBtn.classList.remove("active");
            heartImg.firstElementChild.src = "/images/heart-empty.png";
            people.textContent--;
            interestBtn.lastElementChild.src = "/images/plus.png";
            interestBtn.firstElementChild.textContent = "관심 추가";
            message = `나의 관심 의사에서 취소했습니다.`;
            showWarnModal(message);
        } else {
            console.log(e.target);

            message = `해당 의사를 나의 관심 의사로 <br>등록 했습니다.`;
            interestBtn.lastElementChild.src = "/images/check.png";
            heartImg.firstElementChild.src = "/images/heart.png";
            people.textContent++;
            console.log(heartImg.lastElementChild.textContent);
            interestBtn.classList.add("active");
            interestBtn.firstElementChild.textContent = "관심 중";
            showWarnModal(message);
        }

        setTimeout(() => {
            buttonsCheck = true;
        }, 2000);
    } else if (addressKakao) {
        e.stopPropagation();
        e.preventDefault();
        console.log("카카오 나중에 해야하는 부분");
    }
});

// 헤더 바로 밑에 부분 버튼 모음들
const coachMarkBtns = document.querySelectorAll(
    "ul.coach-mark-container li button"
);
// 원장 병원 기타(미정 삭제할 수 도 있음) 버튼 클릭 이벤트 일단은
// 색깔만 변하게
coachMarkBtns.forEach((coachMarkBtn) => {
    coachMarkBtn.addEventListener("click", (e) => {
        const temp = document.querySelector(
            "ul.coach-mark-container li button span.active"
        );
        temp.classList.remove("active");
        e.target.classList.add("active");
    });
});

let clickable = true;
const cardListOrderBtn = document.querySelector(".card-list-order-btn");
cardListOrderBtn.addEventListener("click", (e) => {
    if (!clickable) return; // 클릭 무시
    clickable = false;
    const spanTag = document.querySelector(".card-list-btn-text");
    if (spanTag.classList[1]) {
        console.log(1);
        spanTag.classList.remove("change-order");
        spanTag.textContent = "답변왕 순";
    } else {
        console.log(2);
        spanTag.classList.add("change-order");
        spanTag.textContent = "선호도 순";
    }
    setTimeout(() => {
        clickable = true;
    }, 500);
});

// 나중에 서버에 보낼 문자열 배열 부분
// 클릭 시 여기에 추가, 삭제
// 삭제는 아마 indexOf로 찾아서 하지 않을까 싶음
// 카테고리 버튼들
const categoryListBtns = document.querySelectorAll(
    "ul.category-list-wrap li button.category-btn"
);

const categoryList = [];
categoryListBtns.forEach((categoryListBtn) => {
    categoryListBtn.addEventListener("click", (e) => {
        let text = categoryListBtn.firstElementChild.textContent;
        if (categoryListBtn.classList[1]) {
            categoryListBtn.classList.remove("checked");
            // 해당 부분에서 배열 원소 값 삭제
            categoryList.splice(categoryList.indexOf(text));
        } else {
            categoryListBtn.classList.add("checked");
            // 해당 부분에서 배열 원소 추가 해주기
            categoryList.push(text);
        }
    });
});

// 선택한 카테고리 모두 취소
// 선택 취소 버튼
const categorySelectCancleAll = document.querySelector(
    ".category-select-btn-del"
);
categorySelectCancleAll.addEventListener("click", (e) => {
    categoryListBtns.forEach((categoryListBtn) => {
        let text = null;
        if (categoryListBtn.classList[1]) {
            text = categoryListBtn.firstElementChild.textContent;
            categoryListBtn.classList.remove("checked");
            // 해당 부분에서 배열 원소 값 삭제
            categoryList.splice(categoryList.indexOf(text));
        }
    });
});

// 선택한 카테고리들로 보여주도록 하고 모달 창 닫기
// 아무것도 선택 안하면 전체보여주기
// 선택 완료 버튼
const categoryFinalSelect = document.querySelector(".category-select-fix-btn");
const categoryModal = document.querySelector(".category-modal");
const categoryModalOpenBtnText = document.querySelector(
    "button.category-container-modal-btn span.check-list"
);
categoryFinalSelect.addEventListener("click", (e) => {
    // console.log(categoryList == false);
    if (categoryList.length === 0) {
        console.log(11);
        categoryModalOpenBtnText.textContent = "전체";
    } else {
        console.log(categoryList);
        categoryModalOpenBtnText.textContent =
            categoryList.length > 1
                ? `${categoryList[0]} 외 ${categoryList.length - 1}개`
                : `${categoryList[0]}`;
        console.log(categoryModalOpenBtnText.textContent);
    }

    categoryModal.style.display = "none";
});

// 카테고리 모달 넣기
const categoryModalOpenBtn = document.querySelector(
    "button.category-container-modal-btn"
);
categoryModalOpenBtn.addEventListener("click", (e) => {
    categoryModal.style.display = "flex";
});

/**************** */

// let itemIndex = 0;
// // 만약 데이터가 없으면 무한 스크롤 안하기 일단은 주석 처리
// const maxItems = 20; // 총 20개까지만 로딩 나중에 여기에 글 목록 개수 가져오기
// const loadCount = 5; // 한 번에 5개씩

// // 아이템 5개씩 로딩하는 함수
// function loadItems(count = 5) {
//     const remaining = maxItems - itemIndex;
//     const countToLoad = Math.min(loadCount, remaining);
//     for (let i = 0; i < countToLoad; i++) {
//         listLayout.innerHTML += `
//         <li>
//             <div class="content-info">
//                 <img src="https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp" width="48" height="48" alt="" class="contentInfoImg" style="color: transparent">
//                 <div class="content-info-text">
//                     <div class="type-user-wrapper">
//                         <div class="depart-treatment">
//                             <div class="doctor-info">
//                                 <span class="doctorinfo-name">
//                                     의사 이름
//                                 </span>
//                             </div>
//                             <div class="depart-treatment-wrap">
//                                 <span class="first">
//                                     진료 과목
//                                 </span>
//                                 <span class="second">
//                                     안과
//                                 </span>
//                             </div>

//                         </div>
//                     </div>
//                     <!-- 주소 클릭 이벤트 -->
//                     <div class="info-container">
//                         <span class="first">
//                             소속 병원
//                         </span>
//                         <span class="second">
//                             서울 백병원
//                         </span>
//                         <span class="first detail-address">
//                             상세 주소
//                         </span>
//                         <span class="second">
//                             서울 노원구 동일로 1342 상계백병원
//                         </span>
//                     </div>
//                     <!-- /주소 클릭 이벤트 -->
//                     <div class="info-container">
//                         <span class="first">
//                             병원 전화 번호
//                         </span>
//                         <span class="second">
//                             02-123-1234
//                         </span>

//                     </div>
//                     <div class="icon-btn-wrap">
//                         <div class="icon-container">
//                             <img src="/images/DrIcon-Photoroom.png" with="35" height="35" alt="">
//                             <span class="greeting">
//                                 <strong>최선</strong>을 다하겠습니다.</span>
//                         </div>
//                         <div class="btn-container">
//                             <div
//                                 class="btn-wrap">
//                                 <!-- 쪽지 클릭 이벤트 -->
//                                 <button class="btnshape mail-send-btn">
//                                     <img src="/images/email.png">
//                                     <span>쪽지 보내기</span>
//                                 </button>
//                                 <!-- /쪽지 클릭 이벤트 -->
//                                 <!-- 관심 추가 클릭 이벤트 -->
//                                 <button class="btnshape interest-btn active">
//                                     <span>관심 중
//                                     </span>
//                                     <img src="/images/check.png">
//                                 </button>
//                                 <!-- /관심 추가 클릭 이벤트 -->
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//     </li>
//         `;
//         itemIndex++;
//     }
// }

// // IntersectionObserver 생성
// const observer = new IntersectionObserver((entries) => {
//     if (entries[0].isIntersecting) {
//         loadItems(); // 추가 아이템 로딩
//         console.log(mailSendBtns);
//     }
// });

// if (itemIndex >= maxItems) {
//     observer.disconnect();
// }

// // sentinel 엘리먼트 관찰 시작
// observer.observe(document.getElementById("intersectionObserverLayout"));

// // 처음에 5개만 로딩
// // loadItems();
/****************** */
