const shiftAdPage = document
    .querySelectorAll(".main-side-ad-container")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("이미지 클릭해서 광고 페이지로 이동");
        });
    });

const shiftSparringPage = document
    .querySelectorAll(".main-side-sparring-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("클릭해서 스파링 페이지로 이동");
        });
    });

 const shiftPopularPage = document
    .querySelectorAll(".main-side-popular-topics-title-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("클릭해서 검색 페이지로 이동");
        });
    });

const shiftPopularKeyword = document
    .querySelectorAll(".main-side-popular-topic-content-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("클릭해서 해당 키워드 검색 페이지로 이동");
        });
    });
    

    