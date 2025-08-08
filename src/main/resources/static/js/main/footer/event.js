const shiftPage = document
    .querySelectorAll(".footer-categories-wrapper a")
    .forEach((element) => {
        element.addEventListener("click", (e) => {
            console.log("클릭해서 페이지 이동");
        });
    });
