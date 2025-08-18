HTMLCollection.prototype.forEach = Array.prototype.forEach;

let modalCheck;
//  광클 예방
let buttonsCheck = true;
// 관심 추가 버튼
const buttons = document.querySelectorAll("button.interest-btn");

//관심 추가-삭제 버튼
const interestBtn = document.querySelector(".interest-btn");

const listLayout = document.getElementById("intersectionObserver");

// 상세보기 아래 버튼들
const profilBtns = document.querySelectorAll(".profil-content-select");
const profilCotentCheck = document.querySelector(".profil-content-selector");
const elements = document.querySelectorAll("[data-cnt]");

profilBtns.forEach((profilBtn) => {
    profilBtn.addEventListener("click", (e) => {
        // console.log(1);
        const cnt = profilBtn.dataset.cnt;
        console.log(profilCotentCheck);
        profilCotentCheck.style.transform = `translateX(calc(${100 * cnt}% + ${
            16 * cnt
        }px))`;
        document.querySelector("span.active").classList.remove("active");
        const spanTag = profilBtn.firstElementChild.firstElementChild;
        spanTag.classList.add("active");
    });
});

/*************** 모달 부분 ***********/
const textWarnCheck = (tag, check = false) => {
    let count = tag.value.length;
    const temp = check
        ? document.querySelector(".content-text-warn.text-warn")
        : document.querySelector(".mail-title-warn.text-warn");
    temp.setAttribute("data-count", count);
    // console.log(111);
};

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
// 의사 검색창에서의 동작

// 관심 추가 삭제
interestBtn.addEventListener("click", (e) => {
    // 관심 형태에 따라 클릭 버튼 다르게
    const button = e.target.closest(".interest-btn");
    const heartImg = document.querySelector(".docterinfo-favorite-wrapper img");
    const people = document.querySelector(".docterinfo-favorite-wrapper span");
    let message = ``;
    if (!buttonsCheck) {
        return;
    }
    buttonsCheck = false;

    if (button.classList[2] === "active") {
        message = `나의 관심 의사에서 취소했습니다.`;
        interestBtn.classList.remove("active");
        interestBtn.lastElementChild.src = "/images/plus.png";
        interestBtn.firstElementChild.textContent = "관심 추가";
        heartImg.src = "/images/heart-empty.png";
        people.textContent--;
        showWarnModal(message);
    } else {
        message = `해당 의사를 나의 관심 의사로 <br>등록 했습니다.`;
        interestBtn.classList.add("active");
        interestBtn.lastElementChild.src = "/images/check.png";
        heartImg.src = "/images/heart.png";
        people.textContent++;
        interestBtn.firstElementChild.textContent = "관심 중";
        showWarnModal(message);
    }

    setTimeout(() => {
        buttonsCheck = true;
    }, 2000);
});

// // 후기 남기기 버튼입니다. (내용이 없을 때)
// const noExistReviewBtn = document.querySelector(".noexist-search-btn");
// let noExistReviewBtnCheck = true;
// noExistReviewBtn.addEventListener("click", (e) => {
//     console.log(1111);
//     const divTag = document.querySelector(".review-register-container");
//     console.log(divTag);
//     noExistReviewBtnCheck
//         ? divTag.classList.add("active")
//         : divTag.classList.remove("active");

//     noExistReviewBtnCheck &&
//         (reviewForm[
//             "reviewContent"
//         ].placeholder = `[나중에 유저 이름] 입력해주세요.`);
//     noExistReviewBtnCheck = !noExistReviewBtnCheck;
// });
// // 후기 남기기 버튼입니다. (내용이 하나라도 있을 때)
// const existReviewBtn = document.querySelector(".exist-review-btn");
// let existReviewBtnCheck = true;
// existReviewBtn.addEventListener("click", (e) => {
//     const divTag = document.querySelector(".review-register-container");
//     console.log(divTag);
//     existReviewBtnCheck
//         ? divTag.classList.add("active")
//         : divTag.classList.remove("active");

//     existReviewBtnCheck &&
//         (reviewForm_v2[
//             "reviewContent"
//         ].placeholder = `[나중에 유저 이름] 입력해주세요.`);
//     existReviewBtnCheck = !existReviewBtnCheck;
// });
const reviewRegBtn = (className, formTag, tagClass) => {
    const btnTag = document.querySelector(`${className}`);
    let btnCheck = true;
    btnTag.addEventListener("click", (e) => {
        // console.log(btnTag);
        console.log(formTag);
        console.log(btnCheck);
        const divTag = document.querySelector(`${tagClass}`);

        if (btnCheck) {
            divTag.classList.add("active");
            formTag[
                "reviewContent"
            ].placeholder = `[나중에 유저 이름] 후기를 입력해주세요.`;
            for (let i = 0; i < 5; i++) {
                svgStars[i].style.fill = "#E0E0E0";
            }
        } else {
            divTag.classList.remove("active");
            document.getElementById("rate").value = 0;
        }
        btnCheck = !btnCheck;
    });
};
// 검색 결과 없을 때
reviewRegBtn(".noexist-search-btn", reviewForm_v1, ".v1");
// 검색 결과 있을 때
reviewRegBtn(".exist-review-btn", reviewForm_v2, ".v2");

// 별점 클릭 버튼들
const reviewStarBtns = document.querySelectorAll("li.star-list-content");
// 별점 이미지들
const svgStars = document.querySelectorAll("div.star-btn-wrap svg path");

reviewStarBtns.forEach((reviewStarBtn) => {
    reviewStarBtn.addEventListener("click", (e) => {
        // 색깔 채워주기
        for (let i = 0; i < 5; i++) {
            if (i <= reviewStarBtn.dataset.point) {
                svgStars[i].style.fill = "#FFD633";
            } else {
                svgStars[i].style.fill = "#E0E0E0";
            }
        }
        // 나중에 별점 폼으로 넘겨주기 위해서
        document.getElementById("rate").value =
            parseInt(reviewStarBtn.dataset.point) + 1;
    });
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

/***************/
// 시간 부분
// function timeForToday(datetime) {
//     const today = new Date();
//     const date = new Date(datetime);

//     let gap = Math.floor((today.getTime() - date.getTime()) / 1000 / 60);

//     if (gap < 1) {
//         return "방금 전";
//     }

//     if (gap < 60) {
//         return `${gap}분 전`;
//     }

//     gap = Math.floor(gap / 60);

//     if (gap < 24) {
//         return `${gap}시간 전`;
//     }

//     gap = Math.floor(gap / 24);

//     if (gap < 31) {
//         return `${gap}일 전`;
//     }

//     gap = Math.floor(gap / 31);

//     if (gap < 12) {
//         return `${gap}개월 전`;
//     }

//     gap = Math.floor(gap / 12);

//     return `${gap}년 전`;
// }

// console.log(timeForToday("2025-07-18T11:45:00"));

// 예시
// posts.forEach((post) => {
// timeForToday(post.createdDate);
// })
/***************/
