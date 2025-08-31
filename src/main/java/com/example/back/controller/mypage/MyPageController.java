package com.example.back.controller.mypage;

import com.example.back.dto.member.MemberDTO;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Slf4j
public class MyPageController {
    private final MemberService memberService;
    private final HttpSession session;
    //    마이페이지 이동
    @GetMapping("")
    public String goToMyPageForm(Model model) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        log.info("{}", memberDTO);

        if (memberDTO == null) {
            Long memberId = (Long) session.getAttribute("memberId");
            return "redirect:/member/loginmain";
        }
        MemberDTO member = memberService.getMemberByIdAllStatus(memberDTO.getId())
                .orElseThrow(IllegalArgumentException::new);

        log.info("member : {}", member);
        model.addAttribute("member", member);


        return "member/mypage/myMember/mypage";
    }

}
