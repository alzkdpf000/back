NodeList.prototype.indexOf = Array.prototype.indexOf;
const categories = document.querySelectorAll(".mypage-category-wrapper a");
let temp = document.querySelector("span.active");

categories.forEach((category) => {
    category.addEventListener("click", (e) => {
        e.preventDefault();
        temp.classList.remove("active");
        temp = category.firstElementChild;
        category.firstElementChild.classList.add("active");
    });
});

function timeForToday(datetime) {
    const today = new Date();
    const date = new Date(datetime);

    let gap = Math.floor((today.getTime() - date.getTime()) / 1000 / 60);

    if (gap < 0) {
        return "방금 전";
    }

    if (gap < 60) {
        return `${gap}분 전`;
    }

    gap = Math.floor(gap / 60);

    if (gap < 24) {
        return `${gap}시간 전`;
    }

    gap = Math.floor(gap / 24);

    if (gap < 31) {
        return `${gap}일 전`;
    }

    gap = Math.floor(gap / 31);

    if (gap < 12) {
        return `${gap}개월 전`;
    }

    gap = Math.floor(gap / 12);

    return `${gap}년 전`;
}

document.querySelectorAll(".mypost-content-time").forEach((time) => {
    const isoTime = time.textContent.trim();
    const relativeTime = timeForToday(isoTime);
    time.textContent = relativeTime;
});

const mypostList = document
    .querySelectorAll(".mypost-list-content-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("클릭");
        });
    });

const leftArrowUseToSwitch = document.querySelectorAll(".mypost-page-arrow-button").forEach((element) => {
    element.addEventListener("click", (e) => {
    console.log("클릭");
    });
});


const switchPage = document
    .querySelectorAll(".mypost-page-button")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("클릭클릭");
        });
    });
