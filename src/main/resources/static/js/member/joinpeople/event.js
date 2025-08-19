document.addEventListener("DOMContentLoaded", function () {
    // 필드 가져오기
    const phone1 = document.getElementById("phone1");
    const phone2 = document.getElementById("phone2");
    const phone3 = document.getElementById("phone3");
    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const passwordConfirmInput = document.getElementById("passwordConfirm");
    const signupBtn = document.getElementById("loginBtn");

    // 이메일 중복체크 상태 저장
    let emailAvailable = false;

    // 이메일 메시지 span
    const check = document.querySelector(".iprukchang1");
    const emailCheckMessage = document.createElement("span");
    emailCheckMessage.id = "emailCheckMessage";
    check.appendChild(emailCheckMessage);

    // 숫자만 입력 & 자동 포커스 함수
    function autoTab(currentInput, nextInput) {
        currentInput.addEventListener("input", function () {
            this.value = this.value.replace(/[^0-9]/g, "");
            if (this.value.length === this.maxLength && nextInput) {
                nextInput.focus();
            }
            checkAllInputs();
        });
    }

    // 유효성 검사 + 버튼 활성화
    function checkAllInputs() {
        let allValid = true;

        // 이름
        if (nameInput.value.trim() === "") {
            nameInput.style.borderColor = "red";
            allValid = false;
        } else {
            nameInput.style.borderColor = "blue";
        }

        // 이메일
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(emailInput.value.trim()) || !emailAvailable) {
            emailInput.style.borderColor = "red";
            allValid = false;
        } else {
            emailInput.style.borderColor = "blue";
        }

        // 비밀번호 (최소 6자리, 문자+숫자)
        const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
        if (!pwPattern.test(passwordInput.value.trim())) {
            passwordInput.style.borderColor = "red";
            allValid = false;
        } else {
            passwordInput.style.borderColor = "blue";
        }

        // 비밀번호 확인
        if (passwordInput.value !== passwordConfirmInput.value || passwordConfirmInput.value === "") {
            passwordConfirmInput.style.borderColor = "red";
            allValid = false;

        } else {
            passwordConfirmInput.style.borderColor = "blue";
        }


        // 핸드폰
        const phoneCheck = (input, maxLen) => {
            if (input.value.length !== maxLen) {
                input.style.borderColor = "red";
                allValid = false;

            } else {
                input.style.borderColor = "blue";
            }
        };
        phoneCheck(phone1, 3);
        phoneCheck(phone2, 4);
        phoneCheck(phone3, 4);

        signupBtn.disabled = !allValid;
        signupBtn.style.backgroundColor = allValid ? "blue" : "";
        signupBtn.style.color = allValid ? "white" : "";
        signupBtn.style.cursor = allValid ? "pointer" : "default";
    }

    // 핸드폰번호 자동탭
    autoTab(phone1, phone2);
    autoTab(phone2, phone3);
    autoTab(phone3, null);


    [nameInput, emailInput, passwordInput, passwordConfirmInput, phone1, phone2, phone3]
        .forEach(input => input.addEventListener("input", checkAllInputs));

    // 이메일 중복 체크 (블러 이벤트)
    emailInput.addEventListener("blur", () => {
        const email = emailInput.value.trim();
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!email || !emailPattern.test(email)) {
            emailCheckMessage.textContent = "이메일 형식 오류";
            emailCheckMessage.style.color = "red";
            emailAvailable = false;
            checkAllInputs();
            return;
        }

        fetch("check-email", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ memberEmail: email })
        })
            .then(res => res.json())
            .then(data => {
                if (data.isExist) {
                    emailCheckMessage.textContent = "사용 불가";
                    emailCheckMessage.style.color = "red";
                    emailAvailable = false;
                } else {
                    emailCheckMessage.textContent = "사용 가능";
                    emailCheckMessage.style.color = "green";
                    emailAvailable = true;
                }
                checkAllInputs();
            })
            .catch(() => {
                emailCheckMessage.textContent = "이메일 확인 중 오류 발생";
                emailCheckMessage.style.color = "orange";
                emailAvailable = false;
                checkAllInputs();
            });
    });
    
    checkAllInputs();
});

const addressInput = document.getElementById("address");
const addressDetailInput = document.getElementById("addressDetail");

const addressBtn = document.getElementById("addressBtn");

addressBtn.addEventListener("click", (e) => {
    new daum.Postcode({
        oncomplete: function (data) {
            // 도로명 주소 넣기
            addressInput.value = data.roadAddress;
            // 상세주소 input에 포커스 주기
            addressDetailInput.focus();
            // 유효성 검사 다시 실행
            checkAllInputs();
        }
    }).open();
});

const formTag = document.getElementById("formTag");
const memberPhone = document.getElementById(("memberPhone"));
formTag.addEventListener("sudmit", (e) => {
    e.preventDefault()

    // 전화번호 3개를 합쳐서 히든 input에 넣기
    memberPhone.value = `#{phone1.value}-#{phone2.value}-#{phone3.value}`;

    formTag.submit();

});