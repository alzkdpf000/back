let modalCheck;
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

/**
 *  이미지 파일 부분입니다.
 * 이미지 파일 지우고 넣고 최대 일단 3개까지만 들어가도록 설정
 * 나중에 수정 필요 나중에는 fileImgList, fileCheck로 체크해서
 * 서버 넣겨주기
 *
 */
// 이미지 파일 버튼
const inputFileBtn = document.getElementById("create-question-image");
// 이미지 파일 연달아 붙이는 공간
const ulTag = document.getElementById("file-img-list");

FileList.prototype.forEach = Array.prototype.forEach;
// 이미지 정보들이 들어가는 자리
const fileImgList = new Array();
// 어떤 이미지가 삭제되었는지 체크
const fileCheck = new Array(3).fill(false);
// 이미지 버튼 클릭하면 아예 초기화
inputFileBtn.addEventListener("click", (e) => {
    console.log(123);
    fileImgList.length = 0;
    fileCheck.fill(false);
    const lis = Array.from(
        document.getElementsByClassName("thumbnail-list-content")
    );
    lis.forEach((li) => {
        li.remove();
    });
});

inputFileBtn.addEventListener("change", (e) => {
    console.log(123141231);
    const files = e.target.files;
    let cnt = 0;
    files.forEach((file) => {
        const reader = new FileReader();
        const imgTag = document.createElement("img");
        const divBtn = document.createElement("div");
        const liTag = document.createElement("li");
        const divTag = document.createElement("div");
        reader.readAsDataURL(file);
        divBtn.id = "file-cancel-btn";
        liTag.classList.add("thumbnail-list-content");
        divBtn.dataset.position = cnt;
        imgTag.classList.add("thumbnail");
        divTag.classList.add(`cancel-btn-${cnt++}`);
        divTag.classList.add("cancle");
        reader.addEventListener("load", (e) => {
            const path = e.target.result;
            if (fileImgList.length > 2) {
                return;
            } else {
                fileImgList.push(path);
            }
            if (path.includes("image")) {
                imgTag.style.backgroundImage = `url(${path})`;
                ulTag.appendChild(liTag);
                liTag.appendChild(imgTag);
                liTag.appendChild(divTag);
                divTag.appendChild(divBtn);
            }
            divTag.style.display = "block";

            // 취소 버튼 누르면 썸네일 삭제하기
            divTag.addEventListener("click", (e) => {
                console.log(e.target.parentElement.parentElement);
                fileCheck[e.target.dataset.position] = true;
                e.target.parentElement.parentElement.remove();
            });
        });
    });
    inputFileBtn.value = "";
});

// console.log(document.querySelector(".accepted-btn-container").parentElement);

// 채택하기/ 채택 중 클릭 이벤트
const doctorList = document.querySelector(".doctor-answer-list");
console.log(doctorList);
doctorList.addEventListener("click", (e) => {
    if (e.target.closest(".accepted-answer-container")) {
        const parent = e.target.closest(".accepted-answer-container");
        const span = parent.querySelector("div.accepted-text span");
        const divTag = parent.querySelector("div.toggle-img");
        const siblingTag = parent.previousElementSibling;
        console.log(parent);
        console.log(siblingTag);
        const divSiblingTag = siblingTag.querySelector(
            "div.accepted-icon-wrap"
        );
        if (span.classList[0]) {
            span.classList.remove("accepted");
            divTag.classList.remove("accepted");
            span.textContent = "채택하기";
            divSiblingTag.classList.remove("accepted");
            showWarnModal("이 답변의 채택을 해제하였습니다.");
        } else {
            span.classList.add("accepted");
            span.textContent = "채택 중";
            divTag.classList.add("accepted");
            divSiblingTag.classList.add("accepted");
            showWarnModal("이 답변을 채택하였습니다.");
        }
    }
});

// 마우스 호버
const header = document.querySelector(".header-container");
const tagLayout = document.querySelector(".department-list-container");
header.addEventListener("mouseenter", (e) => {
    tagLayout.classList.add("active");
});

header.addEventListener("mouseleave", (e) => {
    tagLayout.classList.remove("active");
});

// div.header-wrap{
//     position
// }
