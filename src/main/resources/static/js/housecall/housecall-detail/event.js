const acceptBtn = document.querySelector(".accept-btn");
acceptBtn.addEventListener("click", (e) => {
    if (acceptBtn.classList[1]) {
        acceptBtn.classList.remove("active");
        acceptBtn.textContent = "승인하기";
    } else {
        acceptBtn.classList.add("active");
        acceptBtn.textContent = "진료예정";
    }
});
