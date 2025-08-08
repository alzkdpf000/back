let modalCheck;
let buttonsCheck = true;

// 시간 부분
function timeForToday(datetime) {
    const today = new Date();
    const date = new Date(datetime);

    let gap = Math.floor((date.getTime() - today.getTime()) / 1000 / 60);
    // console.log(today.getTime() - date.getTime());
    // console.log(date.getTime() - today.getTime());
    if (gap < 1) {
        return "곧 마감";
    }

    if (gap < 60) {
        return `${gap}분`;
    }

    gap = Math.floor(gap / 60);

    if (gap < 24) {
        return `${gap}시간`;
    }

    gap = Math.floor(gap / 24);

    if (gap < 31) {
        return `${gap}일`;
    }

    gap = Math.floor(gap / 31);

    if (gap < 12) {
        return `${gap}개월`;
    }

    gap = Math.floor(gap / 12);

    return `${gap}년`;
}

// console.log(timeForToday("2025-07-18T11:45:00"));
console.log(document.querySelector(".remain-time-span"));
document.querySelector(".remain-time-span").innerHTML = `
<svg fill="none" height="14" viewBox="0 0 14 14" width="14" xmlns="http://www.w3.org/2000/svg"><g clip-path="url(#clip0_6147_7791)"><path clip-rule="evenodd" d="M6.99992 13.4167C10.5437 13.4167 13.4166 10.5439 13.4166 7.00004C13.4166 3.45621 10.5437 0.583374 6.99992 0.583374C3.45609 0.583374 0.583252 3.45621 0.583252 7.00004C0.583252 10.5439 3.45609 13.4167 6.99992 13.4167ZM7.75837 4.08338C7.75837 3.66457 7.41885 3.32505 7.00003 3.32505C6.58122 3.32505 6.2417 3.66457 6.2417 4.08338V7.29172C6.2417 7.47274 6.30646 7.64779 6.42426 7.78523L8.17426 9.8269C8.44683 10.1449 8.92556 10.1817 9.24355 9.90915C9.56154 9.63659 9.59836 9.15785 9.3258 8.83987L7.75837 7.01119V4.08338Z" fill="var(--color-gray-600)" fill-rule="evenodd"></path></g><defs><clipPath id="clip0_6147_7791"><rect fill="white" height="14" width="14"></rect></clipPath></defs></svg>
${timeForToday("2025-07-29T20:12:00")}`;
// 시간 부분 //

// 예시
// posts.forEach((post) => {
// timeForToday(post.createdDate);
// })

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

const listLayout = document.getElementById("intersectionObserver");

listLayout.addEventListener("click", (e) => {
    const housecallBtn = e.target.closest(".housecall-btn");
    const addressKakao = e.target.closest(".hospital-info");
    // 모달 전에 쪽지 보내기 누르면 모달이 보이게 하기
    if (housecallBtn) {
        // 관심 형태에 따라 클릭 버튼 다르게
        let message = ``;
        if (!buttonsCheck) {
            return;
        }
        buttonsCheck = false;

        if (housecallBtn.classList[2] === "active") {
            housecallBtn.classList.remove("active");
            housecallBtn.firstElementChild.src = "/images/wait.png";
            housecallBtn.lastElementChild.textContent = "진료 대기";
            console.log(e.target);
            message = `진료를 취소하였습니다.`;
            showWarnModal(message);
        } else {
            console.log(e.target);
            housecallBtn.classList.add("active");
            housecallBtn.firstElementChild.src = "/images/check-mark.png";
            housecallBtn.lastElementChild.textContent = "진료 승인";
            message = `진료를 승인하였습니다.`;
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

let clickable = true;
const cardListOrderBtn = document.querySelector(".card-list-order-btn");
cardListOrderBtn.addEventListener("click", (e) => {
    if (!clickable) return; // 클릭 무시
    clickable = false;
    const spanTag = document.querySelector(".card-list-btn-text");
    if (spanTag.classList[1]) {
        // console.log(1);
        spanTag.classList.remove("change-order");
        spanTag.textContent = "마감 순";
    } else {
        // console.log(2);
        spanTag.classList.add("change-order");
        spanTag.textContent = "위험 순";
    }
    setTimeout(() => {
        clickable = true;
    }, 500);
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
