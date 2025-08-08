// const listLayout = document.getElementById("intersectionObserver");
// /**************** */

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
//                                         <!-- 클릭 버튼 -->
//                                         <a class="link-click">
//                                             <div class="content-info">
//                                                 <img src="https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp" width="50" height="50" alt="" class="contentInfoImg" style="color: transparent">
//                                                 <div class="content-info-text">
//                                                     <div class="type-user-wrapper">
//                                                         <div class="depart-treatment">
//                                                             <div class="mail-doctor-info doctor-info">
//                                                                 <div class="doctor-info-wrap">
//                                                                     <span class="doctorinfo-name">
//                                                                         의사 이름
//                                                                     </span>
//                                                                     <span>관심 수 : 10</span>
//                                                                 </div>
//                                                                 <div>
//                                                                     <span class="first">
//                                                                         진료 과목
//                                                                     </span>
//                                                                     <span class="second">
//                                                                         안과
//                                                                     </span>
//                                                                 </div>
//                                                                 <span class="doctor-introduction">
//                                                                     안녕하세요. 테스트 문장입니다. 잠시 실례하겠습니다. 화내지마세요. 반가습니다. 안녕하세요. 잘있어요. 다시 만나요.
//                                                                 </span>
//                                                             </div>
//                                                         </div>
//                                                     </div>
//                                                 </div>
//                                             </div>
//                                         </a>
//                                         <!-- /클릭 버튼 -->
//                                     </li>
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
// /****************** */
