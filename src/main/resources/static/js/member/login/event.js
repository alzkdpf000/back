// 카카오 로그인
const kakaoLoginButton = document.getElementById("kakao-login");
kakaoLoginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const type = kakaoLoginButton.dataset.type
    window.location.href =
        `https://kauth.kakao.com/oauth/authorize?client_id=b89acf62a1fdb8335aaec795cdc5912a&redirect_uri=http://localhost:10000/kakao/login&response_type=code&state=${type}`;
});
