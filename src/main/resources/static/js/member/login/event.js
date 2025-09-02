// 카카오 로그인
const kakaoLoginButton = document.getElementById("kakao-login");
kakaoLoginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const type = kakaoLoginButton.dataset.type
    window.location.href =
        `https://kauth.kakao.com/oauth/authorize?client_id=bc6e9a6f6f2e50cfe91c6a95760d5502&redirect_uri=http://3.37.55.123:10000/kakao/login&response_type=code&state=${type}`;
});
