package com.example.back.controller.member;

import com.example.back.dto.member.MemberDTO;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final MemberDTO memberDTO;

    @GetMapping("join")
    public String goToJoinForm(Model model){
        model.addAttribute("MemberDTO",memberDTO);
        return "/join";
    }

    @PostMapping("join")
    public RedirectView join(Model model){
        memberService.join(memberDTO);
        return new RedirectView("member/login");
    }


}
