const header = document.getElementById('header');

let lastScrollY = 0;

window.addEventListener('scroll', () => {
    if (window.scrollY > 5) {
        header.classList.add('show');
    } else {
        header.classList.remove('show');
    }
});