document.addEventListener("DOMContentLoaded", function () {
    const inputs = [
        document.getElementById("name"),
        document.getElementById("phone1"),
        document.getElementById("phone2"),
        document.getElementById("phone3"),
        document.getElementById("password"),
        document.getElementById("passwordConfirm"),
    ];

    const button = document.getElementById("loginBtn");

    function checkInputsFilled() {
        const allFilled = inputs.every(
            (input) => input && input.value.trim() !== ""
        );

        if (allFilled) {
            button.disabled = false;
            button.classList.add("active-button");
        } else {
            button.disabled = true;
            button.classList.remove("active-button");
        }
    }

    inputs.forEach((input) => {
        if (input) {
            input.addEventListener("input", checkInputsFilled);
        }
    });

    // 👉 핸드폰 번호 입력 시 자동 탭 이동
    const phone1 = document.getElementById("phone1");
    const phone2 = document.getElementById("phone2");
    const phone3 = document.getElementById("phone3");

    if (phone1 && phone2) {
        phone1.addEventListener("input", function () {
            if (this.value.length === this.maxLength) {
                phone2.focus();
            }
        });
    }

    if (phone2 && phone3) {
        phone2.addEventListener("input", function () {
            if (this.value.length === this.maxLength) {
                phone3.focus();
            }
        });
    }
});
