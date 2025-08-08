document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const loginBtn = document.getElementById("loginBtn");

    function updateButtonState() {
        const emailFilled = emailInput && emailInput.value.trim() !== "";
        const passwordFilled =
            passwordInput && passwordInput.value.trim() !== "";

        if (emailFilled && passwordFilled) {
            loginBtn.disabled = false;
            loginBtn.classList.add("active-button");
        } else {
            loginBtn.disabled = true;
            loginBtn.classList.remove("active-button");
        }
    }

    if (emailInput && passwordInput && loginBtn) {
        emailInput.addEventListener("input", updateButtonState);
        passwordInput.addEventListener("input", updateButtonState);
    }
});
