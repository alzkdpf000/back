package com.example.back.controller.mail;

import com.example.back.service.mail.MailService;
import com.example.back.service.member.MemberService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {
    private final MailService mailService;
    private final MemberService memberService;

    //    이메일 메시지
    @PostMapping("send")
    public RedirectView send(@RequestParam("memberEmail") String email,
                             HttpServletRequest request,
                             HttpServletResponse response) throws MessagingException {

        mailService.sendMail(email, request, response);

        Cookie emailCookie = new Cookie("email", email);
        emailCookie.setPath("/");
        emailCookie.setMaxAge(60 * 5);
        response.addCookie(emailCookie);

        return new RedirectView("/member/emailsuccess");
    }


    @GetMapping("emailsuccess")
    public RedirectView findEmail(@CookieValue(name = "code", required = false) String cookieCode,
                                  @CookieValue(name = "email", required = false) String email,
                                  String code,
                                  HttpServletResponse response) {
        log.info("cookieCode={}", cookieCode);
        log.info("code={}", code);
        log.info("email={}", email);

        if (cookieCode == null || email == null || !cookieCode.equals(code)) {
            return new RedirectView("/mail/emailfail");
        }

        // 쿠키 삭제
        Cookie codeCookie = new Cookie("code", null);
        codeCookie.setMaxAge(0);
        codeCookie.setPath("/");
        response.addCookie(codeCookie);

        Cookie emailCookie = new Cookie("email", null);
        emailCookie.setMaxAge(0);
        emailCookie.setPath("/");
        response.addCookie(emailCookie);

        return new RedirectView("/mail/password-reset?memberEmail=" + email);
    }


    @GetMapping("password-reset")
    public String goToSuccess(@RequestParam("memberEmail") String memberEmail, Model model){
        model.addAttribute("memberEmail", memberEmail);
        return "/member/password-reset";
    }

    @GetMapping("emailfail")
    public String goToFail(){
        return "/member/emailfail";
    }


    @PostMapping("update/password")
    public RedirectView updatePassword(@RequestParam("memberEmail") String memberEmail,
                                       @RequestParam("memberPassword") String memberPassword,
                                       @RequestParam("passwordConfirm") String passwordConfirm) {
        log.info("updatePassword 호출됨: email={}, password={}", memberEmail, memberPassword);

        if (!memberPassword.equals(passwordConfirm)) {
            return new RedirectView("/mail/emailfail");
        }
        memberService.updatePassword(memberEmail, memberPassword);
        return new RedirectView("/member/login");
    }







}
