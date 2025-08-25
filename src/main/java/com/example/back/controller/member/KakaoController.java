package com.example.back.controller.member;

import com.example.back.dto.member.MemberDTO;
import com.example.back.service.member.KakaoService;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoController {
    private final MemberService memberService;
    private final HttpSession session;
    private final KakaoService kakaoService;

    //    카카오 일반회원 로그인
    @GetMapping("/kakao/login")
    public RedirectView kakaoLogin(String code, HttpServletResponse response, String state, RedirectAttributes redirectAttributes) {
        log.info(state);
        String token = kakaoService.getKakaoAccessToken(code);
        Optional<MemberDTO> foundMember = kakaoService.getKakaoInfo(token);
        log.info(foundMember.toString());
        MemberDTO member = foundMember.orElseThrow(RuntimeException::new);
        String path = null;


//        최초 로그인인지 검사
        Optional<MemberDTO> foundKakaoMember = memberService.getKakaoMember(member.getKakaoEmail());
        if (foundKakaoMember.isEmpty()) {
            if (state.equals("doctor")) {
                redirectAttributes.addFlashAttribute("member", member);
                return new RedirectView("/doctor/join");
            }
            if (member.getMemberPhone() == null) {
                member.setMemberPhone("000-0000-0000");
            }
            memberService.joinKakaoMember(member);
            foundKakaoMember = memberService.getKakaoMember(member.getKakaoEmail());
        }

        Cookie accessTokenCookie = new Cookie("accessToken", token);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(accessTokenCookie);

        session.setAttribute("member", foundKakaoMember.get());
        log.info("로그인 후 세션 ID: {}", session.getId());
        log.info("로그인 후 세션 member: {}", session.getAttribute("member"));
        return new RedirectView("/");
    }


}