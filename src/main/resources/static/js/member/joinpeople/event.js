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
    const hospitalPhone = document.getElementById("hospitalPhone");
    const addressInput = document.getElementById("address");
    const addressDetailInput = document.getElementById("addressDetail");
    const addressBtn = document.getElementById("addressBtn");
    const formTag = document.getElementById("formTag");

    // 이메일 중복 체크 상태
    let emailAvailable = false;

    // 이메일 메시지 span
    const check = document.querySelector(".iprukchang1");
    const emailCheckMessage = document.createElement("span");
    emailCheckMessage.id = "emailCheckMessage";
    if(check) check.appendChild(emailCheckMessage);

    // 숫자만 입력 & 자동 포커스
    function autoTab(currentInput, nextInput) {
        currentInput.addEventListener("input", function () {
            this.value = this.value.replace(/[^0-9]/g, "");
            if (this.value.length === this.maxLength && nextInput) nextInput.focus();
            checkAllInputs();
        });
    }

    autoTab(phone1, phone2);
    autoTab(phone2, phone3);
    autoTab(phone3, null);

    // 유효성 검사 + 버튼 활성화
    function checkAllInputs() {
        let allValid = true;

        // 이름
        if (!nameInput.value.trim()) { nameInput.style.borderColor = "red"; allValid = false; }
        else nameInput.style.borderColor = "blue";

        // 이메일
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(emailInput.value.trim()) || !emailAvailable) {
            emailInput.style.borderColor = "red";
            allValid = false;
        } else {
            emailInput.style.borderColor = "blue";
        }

        // 비밀번호
        const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
        if (!pwPattern.test(passwordInput.value.trim())) { passwordInput.style.borderColor = "red"; allValid = false; }
        else passwordInput.style.borderColor = "blue";

        // 비밀번호 확인
        if (passwordInput.value !== passwordConfirmInput.value || passwordConfirmInput.value === "") {
            passwordConfirmInput.style.borderColor = "red"; allValid = false;
        } else passwordConfirmInput.style.borderColor = "blue";

        // 핸드폰 번호
        const phoneCheck = (input, maxLen) => { if (input.value.length !== maxLen) { input.style.borderColor = "red"; allValid = false; } else { input.style.borderColor = "blue"; } };
        phoneCheck(phone1, 3); phoneCheck(phone2, 4); phoneCheck(phone3, 4);

        // 버튼 활성화
        signupBtn.disabled = !allValid;
        signupBtn.style.backgroundColor = allValid ? "blue" : "";
        signupBtn.style.color = allValid ? "white" : "";
        signupBtn.style.cursor = allValid ? "pointer" : "default";

        return allValid;
    }

    // 입력 이벤트에 checkAllInputs 연결
    [phone1, phone2, phone3, nameInput, emailInput, passwordInput, passwordConfirmInput]
        .forEach(input => input.addEventListener("input", checkAllInputs));

    // 이메일 중복 체크
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

    // 주소 찾기
    addressBtn.addEventListener("click", () => {
        console.log("3232");
        new daum.Postcode({
            oncomplete: function (data) {
                addressInput.value = data.roadAddress;
                addressDetailInput.focus();
                checkAllInputs();
            }
        }).open();
    });


    formTag.addEventListener("submit", (e) => {
        e.preventDefault();

        const phoneValue = `${phone1.value}-${phone2.value}-${phone3.value}`;

        // 일반회원이면 memberPhone
        const memberPhone = document.getElementById("memberPhone");
        if(memberPhone) memberPhone.value = phoneValue;

        // 의사회원이면 hospitalPhone
        if(hospitalPhone) hospitalPhone.value = phoneValue;

        formTag.submit();
    });


    checkAllInputs();
});
