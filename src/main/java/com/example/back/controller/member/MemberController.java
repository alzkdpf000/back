package com.example.back.controller.member;

import com.example.back.dto.member.MemberDTO;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j

public class MemberController {
    private final MemberService memberService;
    private final HttpSession session;

//    회원가입
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
        return "/member/login";
    }
//    이메일 중복 검사
    @PostMapping("check-email")
    @ResponseBody
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> member){
        String memberEmail = member.get("memberEmail");
        boolean isExist = memberService.isExistMemberEmail(memberEmail);
        Map<String, Object> result = new HashMap<>();
        result.put("memberEmail", memberEmail);
        result.put("isExist", isExist);

        if(isExist){
//            409 중복처리
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        return ResponseEntity.ok().body(result);
    }


}
