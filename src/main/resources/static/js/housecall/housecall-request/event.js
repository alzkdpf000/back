// 전화번호 앞자리 선택 클릭 부분 //
const select = document.getElementById("phone-numer-content");
const options = document.querySelectorAll(".option");
let tempText = null;
select.addEventListener("click", (e) => {
    options.forEach((option) => {
        if (option.classList[2]) {
            option.classList.remove("show");
            select.style.border = "1px solid #f2f4f6";
        } else {
            option.classList.add("show");
            select.style.border = "1px solid #333d4b";
        }
    });
});

console.log(options);
options.forEach((option) => {
    console.log(123);
    option.addEventListener("click", (e) => {
        // console.log(123);
        temp = select.firstElementChild.textContent;
        select.firstElementChild.textContent = option.textContent;
        option.textContent = temp;
    });
});
// 전화번호 앞자리 선택 클릭 부분 //

// 인풋에다가 포커스 블러 처리 해주기 //
const inputTags = document.querySelectorAll("input[type=text].reg");
const textArea = document.querySelector("textarea");
inputTags.forEach((inputTag) => {
    inputTag.addEventListener("focus", (e) => {
        inputTag.style.border = "1px solid #333d4b";
    });

    inputTag.addEventListener("blur", (e) => {
        inputTag.removeAttribute("style");
    });
});

textArea.addEventListener("focus", (e) => {
    textArea.parentElement.style.border = "1px solid #333d4b";
});

textArea.addEventListener("blur", (e) => {
    textArea.parentElement.removeAttribute("style");
});
// 인풋에다가 포커스 블러 처리 해주기 //

// 아픔 정도 부분 1~5만 입력 나중에 정규식 해야함
const painLevel = document.querySelector("input[type=text].pain-level");
painLevel.addEventListener("input", (e) => {
    if (painLevel.value > 5) {
        painLevel.value = 5;
        return;
    }
    if (painLevel.value === "0") {
        console.log(123);
        painLevel.value = 1;
        return;
    }
});

//  성별 체크
const genderCheck = document.querySelectorAll(".gender");
let checkedGender = document.querySelector(".gender.checked");
genderCheck.forEach((gender) => {
    gender.addEventListener("click", (e) => {
        console.log(gender);
        if (gender !== checkedGender) {
            checkedGender.classList.remove("checked");
            gender.classList.add("checked");
            checkedGender.removeAttribute("name");
            gender.setAttribute("name", "temp13");
            checkedGender = gender;
        }
    });
});

//  제출하기 전에 정규식 검사 하기
//  나중에 휴대폰 번호 이어붙이기
const tempNames = [
    "temp1",
    "temp2",
    "temp3",
    "temp4",
    "temp5",
    "temp6",
    "temp7",
    "temp8",
    "temp9",
    "temp10",
    "temp11",
    "temp12",
    "temp13",
];
const hiddenInput = document.getElementById("phoneHidden");
const reqForm = document.getElementById("reqForm");
reqForm.addEventListener("submit", (e) => {
    let checkNull = true;
    const form = e.target;
    e.preventDefault();

    tempNames.forEach((name) => {
        const field = form[name];
        // name === "temp11" && console.log(field.value);
        const value = field?.value ?? field?.textContent;
        let check = name === "temp11";
        if (!value) {
            check
                ? field.parentElement.classList.add("warn")
                : field.classList.add("warn");
            checkNull = false;
        } else {
            check
                ? field.parentElement.classList.remove("warn")
                : field.classList.remove("warn");
        }
    });
    checkNull && e.target.submit();
});
