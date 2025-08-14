document.addEventListener("DOMContentLoaded", function () {
    // 필드 가져오기
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

// 이메일 중복 검사 블러이벤트
// 이메일 입력 요소
const emailInput = document.getElementById("email");

// 메시지 표시용 span 생성
const emailCheckMessage = document.createElement("span");
emailCheckMessage.id = "emailCheckMessage";
emailInput.parentNode.appendChild(emailCheckMessage);

// 블러 이벤트
emailInput.addEventListener("blur", () => {
    const email = emailInput.value.trim();

    if (!email) {
        emailCheckMessage.textContent = "";
        emailCheckMessage.style.color = "";
        return;
    }

    fetch("/member/check-email", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ memberEmail: email })
    })
        .then(res => {
            if (!res.ok) throw new Error("서버 오류");
            return res.json();
        })
        .then(data => {
            // 서버 응답 키 확인 (여기서는 isExist 사용)
            if (data.isExist) {
                emailCheckMessage.textContent = "이미 사용 중입니다.";
                emailCheckMessage.style.color = "red";
            } else {
                emailCheckMessage.textContent = "사용 가능합니다.";
                emailCheckMessage.style.color = "green";
            }
        })
        .catch(() => {
            emailCheckMessage.textContent = "이메일 확인 중 오류가 발생했습니다.";
            emailCheckMessage.style.color = "orange";
        });
});







