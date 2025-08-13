package com.example.back.controller.member;

import com.example.back.dto.member.MemberDTO;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
@Slf4j

public class MemberController {
    private final MemberService memberService;
    private final HttpSession session;

    @GetMapping("join")
    public String goToJoinForm(MemberDTO memberDTO ,Model model){
        model.addAttribute("memberDTO", memberDTO);
        return "/member/joinpeople";
    }

    @PostMapping("join")
    public RedirectView join( MemberDTO memberDTO){
        memberService.join(memberDTO);
        return new RedirectView("/member/login");
    }

    @GetMapping("login")
    public String goToLoginForm(MemberDTO memberDTO ,Model model){
        model.addAttribute("memberDTO", memberDTO);
        return "member/login";
    }


}
