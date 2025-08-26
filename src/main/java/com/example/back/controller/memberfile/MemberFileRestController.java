package com.example.back.controller.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.service.memberfile.MemberFileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor

public class MemberFileRestController {

    private final MemberFileService memberFileService;

    @GetMapping("profile")
    public MemberFileDTO getMemberFile(HttpSession session){
        Long memberId = (Long) session.getAttribute("memberId");
        return memberFileService.getMemberFile(memberId);
    }
}
