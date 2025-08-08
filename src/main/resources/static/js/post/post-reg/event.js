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
const departmentListWrap = document.querySelector(".department-list-wrap");
const categorySelectCancleAll = document.querySelector(
    ".category-select-btn-del"
);
categorySelectCancleAll.addEventListener("click", (e) => {
    departmentListWrap.innerHTML = "";
    categoryListBtns.forEach((categoryListBtn) => {
        if (categoryListBtn.classList[1]) {
            categoryListBtn.classList.remove("checked");
        }
    });
    categoryList.length = 0;
});

// 선택한 카테고리들로 보여주도록 하고 모달 창 닫기
// 아무것도 선택 안하면 전체보여주기
// 선택 완료 버튼
const categoryFinalSelect = document.querySelector(".category-select-fix-btn");
const categoryModal = document.querySelector(".category-modal");
const categoryModalOpenBtnText = document.querySelector(
    "button.category-container-modal-btn span.check-list"
);
const divContainer = document.querySelector(".department-list-container");
categoryFinalSelect.addEventListener("click", (e) => {
    // console.log(categoryList == false);

    if (categoryList.length !== 0) {
        categoryList.forEach((category) => {
            departmentListWrap.innerHTML += `
            <div class="department-item">
            ${category}
            </div>
            `;
        });
        divContainer.classList.add("active");
    } else {
        divContainer.classList.remove("active");
    }

    categoryModal.style.display = "none";
});

// 카테고리 모달 넣기
const categoryModalOpenBtn = document.querySelector("button.post-tag-add-btn");
categoryModalOpenBtn.addEventListener("click", (e) => {
    categoryModal.style.display = "flex";
    divContainer.classList.remove("active");
    departmentListWrap.innerHTML = "";
});

// 포스트 작성부분 텍스트 영역 포커스
const textareaTag = document.getElementById("postContent");
const textareaTagWrap = document.querySelector(".mail-content-input-container");
textareaTag.addEventListener("focus", (e) => {
    textareaTagWrap.style.borderColor = "black";
});
textareaTag.addEventListener("blur", (e) => {
    textareaTagWrap.style.borderColor = "#f2f4f6";
});

// 포스트 작성하기위한 인풋 태그들
const postInputs = document.querySelectorAll("input[type=text].mail-input");

postInputs.forEach((postInput) => {
    postInput.addEventListener("focus", (e) => {
        if (e.target.style.borderColor !== "red") {
            e.target.style.borderColor = "black";
            return;
        }
    });
    postInput.addEventListener("blur", (e) => {
        if (e.target.style.borderColor !== "red") {
            e.target.removeAttribute("style");
            // slide.style.height = "0";
            // slide.style.opacity = "0";
            return;
        }
    });
});
// 임시로 특정 단어 입력 임시입니다.
postInputs[0].addEventListener("keyup", (e) => {
    if (e.target.value === "A") {
        e.target.style.borderColor = "red";
    } else {
        e.target.style.borderColor = "black";
    }
    textWarnCheck(postInputs[0]);
});

// 글자 수 제한 세기
const textWarnCheck = (tag, check = false) => {
    let count = tag.value.length;
    const temp = check
        ? document.querySelector(".content-text-warn.text-warn")
        : document.querySelector(".mail-title-warn.text-warn");
    temp.setAttribute("data-count", count);
    // console.log(111);
};

const textarea = document.querySelector("#postContent");
textarea.addEventListener("keyup", (e) => {
    textWarnCheck(textarea, true);
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

// 체크박스 영역
const checkBoxDiv = document.querySelector(".label-check-box");
// 체크박스 영역 부분
const label = document.querySelector(".checkbox-label");
// 체크시 이미지 부분
const svg = document.querySelector(".checkbox-svg");
// 전송 버튼
const regBtn = document.querySelector("button.post-reg-btn");
// 체크 박스 클릭 시 보내기 버튼 스타일 변환
checkBoxDiv.addEventListener("click", (e) => {
    if (svg.classList[2]) {
        svg.classList.remove("inactive");
        label.classList.add("inactive");
        checkBoxDiv.style.boxShadow =
            "rgb(126, 165, 242) 0px 0px 0px 2px inset";
        regBtn.disabled = false;
    } else {
        label.classList.remove("inactive");
        svg.classList.add("inactive");
        checkBoxDiv.removeAttribute("style");
        regBtn.disabled = true;
    }
});

let modalCheck;
const showModal = () => {
    modalCheck = false;
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

const postRegCancleBtn = document.querySelector("button.btnshape.cancle-btn");
postRegCancleBtn.addEventListener("click", (e) => {
    document.querySelector("div.warn-modal").style.animation = "popDown 0.5s";
    setTimeout(() => {
        document.querySelector("div.modal").style.display = "none";
    }, 450);
});
