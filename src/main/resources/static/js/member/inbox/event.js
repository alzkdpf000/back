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

const inboxTabs = document.querySelectorAll(".inbox-category-wrapper");
const highlight = document.querySelector(".inbox-category-highlight");
let activeSpan = document.querySelector(".inbox-category-content.active");

inboxTabs.forEach((tab, index) => {
    tab.addEventListener("click", () => {
        activeSpan.classList.remove("active");
        const clickedSpan = tab.querySelector("span");
        clickedSpan.classList.add("active");
        activeSpan = clickedSpan;

        highlight.style.transform = `translateX(${tab.offsetLeft}px)`;
    });
});

window.addEventListener("DOMContentLoaded", () => {
    const activeTab = document
        .querySelector(".inbox-category-wrapper .active")
        .closest(".inbox-category-wrapper");
    const highlight = document.querySelector(".inbox-category-highlight");
    highlight.style.transform = `translateX(${activeTab.offsetLeft}px)`;
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

document.querySelectorAll(".inbox-content-time").forEach((time) => {
    const isoTime = time.textContent.trim();
    const relativeTime = timeForToday(isoTime);
    time.textContent = relativeTime;
});

const doctorInfoUseToName = document
    .querySelectorAll(".inbox-content-name-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("이름 클릭해서 의사정보페이지로 전환");
        });
    });

const doctorInfoUseToImg = document
    .querySelectorAll(".inbox-img-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("이미지 클릭해서 의사정보페이지로 전환");
        });
    });

const checkReceivedMessage = document
    .querySelectorAll(".inbox-content-wrapper")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("받은 쪽지 전문 확인으로 이동");
        });
    });
