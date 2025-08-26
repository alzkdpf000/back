package com.example.back.controller.member;

import com.example.back.common.exception.LoginFailException;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.service.mail.MailService;
import com.example.back.service.member.MemberService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j

public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;
    private final HttpSession session;

    //    회원가입
    @GetMapping("join")
    public String goToJoinForm(MemberDTO memberDTO ,Model model){
        model.addAttribute("memberDTO", memberDTO);
        return "/member/joinpeople";
    }

//    회원가입 처리
    @PostMapping("join")
    public RedirectView join( MemberDTO memberDTO){
        if (!memberService.validateMember(memberDTO)){
            throw new IllegalArgumentException("회원가입 정보가 올바르지 않습니다.");
        }
        memberService.join(memberDTO);
        return new RedirectView("/member/login");
    }

//    회원가입 선택 페이지 (일반, 의사)
    @GetMapping("joinmain")
    public String goToJoinMainForm(){
        return "/member/joinmain";
    }



//    로그인 메인페이지 이동
    @GetMapping("loginmain")
    public String goToLoginForm(MemberDTO memberDTO ,Model model){
        model.addAttribute("memberDTO", memberDTO);
        return "/member/loginmain";
    }

    //    로그인 메인페이지 이동
    @GetMapping("login")
    public String goLoginForm(MemberDTO memberDTO ,String type ,Model model){
        log.info("{}",session.getAttribute("member"));
        log.info("로그인 세션ID={}", session.getAttributeNames());
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("type",type);
        return "/member/login";
    }


//    이메일 중복 검사
    @PostMapping("check-email")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestBody Map<String, String> member) {
        String memberEmail = member.get("memberEmail");
        boolean isExist = memberService.isExistMemberEmail(memberEmail);
        Map<String, Object> result = new HashMap<>();
        result.put("memberEmail", memberEmail);
        result.put("isExist", isExist);

        return result;
    }

//    이메일 로그인 페이지 이동 (이메일 저장 - 쿠키)
    @GetMapping("emaillogin")
    public String goToLoginForm(@CookieValue(name = "remember",  required = false) boolean remember,
                                @CookieValue(name = "remember_member_email", required = false) String rememberdEmail,
                                HttpServletRequest request,
                                MemberDTO memberDTO,
                                Model model){
        memberDTO.setRemember(remember);
        memberDTO.setMemberEmail(rememberdEmail);
        model.addAttribute("memberDTO", memberDTO);

        return "/member/emaillogin";
    }

//    로그인 조회
    @PostMapping("emaillogin")
    public RedirectView login(MemberDTO memberDTO, HttpServletResponse response){

        MemberDTO member = memberService.login(memberDTO).orElseThrow(LoginFailException::new);
        session.setAttribute("member", member);

        Cookie rememberMemberEmailCookie = new Cookie("remember_member_email", memberDTO.getMemberEmail());
        Cookie rememberCookie = new Cookie("remember", String.valueOf(memberDTO.isRemember()));

        rememberMemberEmailCookie.setPath("/");
        rememberCookie.setPath("/");

        if (memberDTO.isRemember()) {
//            30일 유지
            rememberMemberEmailCookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(rememberMemberEmailCookie);

//
            rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(rememberCookie);
        }else {
//            쿠키 삭제
            rememberMemberEmailCookie.setMaxAge(0);
            response.addCookie(rememberMemberEmailCookie);

//
            rememberCookie.setMaxAge(0);
            response.addCookie(rememberCookie);
        }

        return new RedirectView("/");

    }
//    메인페이지로 이동
    @GetMapping("main")
    public RedirectView goToMainForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return new RedirectView("/");
    }

    //    계정 찾기 페이지 이동 (이메일 보내는 페이지)
    @GetMapping("find-email")
    public String goTofindEmailForm(){
        return "/member/emailcheck";
    }

    //    이메일 확인 안내
    @GetMapping("emailsuccess")
    public String goToConfirmForm(){
        return "/member/emailsuccess";
    }
}




