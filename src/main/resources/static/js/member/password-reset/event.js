document.addEventListener("DOMContentLoaded", function () {
    const inputs = [
        document.getElementById("name"),
    ];

    const password = document.getElementById('password');
    const passwordConfirm = document.getElementById('passwordConfirm');
    const loginBtn = document.getElementById('loginBtn');

    function checkPasswords() {
        if (password.value && passwordConfirm.value && password.value === passwordConfirm.value) {
            loginBtn.disabled = false;
        } else {
            loginBtn.disabled = true;
        }
    }

    password.addEventListener('input', checkPasswords);
    passwordConfirm.addEventListener('input', checkPasswords);


    //  핸드폰 번호 입력 시 자동 탭 이동
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
