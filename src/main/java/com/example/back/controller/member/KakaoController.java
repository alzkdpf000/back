package com.example.back.controller.member;

import com.example.back.dto.member.MemberDTO;
import com.example.back.service.member.KakaoService;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService KakaoService;
    private final MemberService memberService;
    private final HttpSession session;
    private final KakaoService kakaoService;

    @GetMapping("/kakao/login")
    public RedirectView kakaoLogin(String code, HttpServletResponse response) {
        String token = kakaoService.getKakaoAccessToken(code);
        Optional<MemberDTO> foundMember = kakaoService.getKakaoInfo(token);
        MemberDTO member = foundMember.orElseThrow(RuntimeException::new);
        String path = null;

//        30일 동안 저장
        Cookie accessTokenCookie = new Cookie("accessToken", token);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(accessTokenCookie);

//        최초 로그인인지 검사
        Optional<MemberDTO> foundKakaoMember = memberService.getKakaoMember(member.getKakaoEmail());
        if (foundKakaoMember.isEmpty()) {
            memberService.joinKakaoMember(member);
        }
        return new RedirectView("/");
    }
}
