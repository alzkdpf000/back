document.addEventListener("DOMContentLoaded", function () {
    // 필드 가져오기
    const ssn1 = document.getElementById("ssn1");
    const ssn2 = document.getElementById("ssn2");
    const phone1 = document.getElementById("phone1");
    const phone2 = document.getElementById("phone2");
    const phone3 = document.getElementById("phone3");
    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const passwordConfirmInput = document.getElementById("passwordConfirm");
    const addressInput = document.getElementById("address");
    const signupBtn = document.getElementById("loginBtn");

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

    // 입력 값 모두 확인
    function checkAllInputs() {
        const allFilled =
            nameInput.value.trim() !== "" &&
            ssn1.value.length === ssn1.maxLength &&
            ssn2.value.length === ssn2.maxLength &&
            emailInput.value.trim() !== "" &&
            passwordInput.value.trim() !== "" &&
            passwordConfirmInput.value.trim() !== "" &&
            addressInput.value.trim() !== "" &&
            phone1.value.length === phone1.maxLength &&
            phone2.value.length === phone2.maxLength &&
            phone3.value.length === phone3.maxLength;

        if (allFilled) {
            signupBtn.disabled = false;
            signupBtn.style.backgroundColor = "blue"; // 파란색으로 변경
            signupBtn.style.color = "white";
            signupBtn.style.cursor = "pointer";
        } else {
            signupBtn.disabled = true;
            signupBtn.style.backgroundColor = ""; // 초기화
            signupBtn.style.color = "";
            signupBtn.style.cursor = "default";
        }
    }

    // 주민등록번호 자동탭
    autoTab(ssn1, ssn2);
    autoTab(ssn2, null);

    // 핸드폰번호 자동탭
    autoTab(phone1, phone2);
    autoTab(phone2, phone3);
    autoTab(phone3, null);

    // 기타 필드 입력 감지
    [
        nameInput,
        emailInput,
        passwordInput,
        passwordConfirmInput,
        addressInput,
    ].forEach((input) => {
        input.addEventListener("input", checkAllInputs);
    });

    // 초기 상태 체크
    checkAllInputs();
});
