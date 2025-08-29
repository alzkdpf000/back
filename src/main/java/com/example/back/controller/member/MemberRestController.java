package com.example.back.controller.member;


import com.example.back.dto.member.MemberDTO;
import com.example.back.service.doctor.DoctorService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final HttpSession session;

    // 현재 로그인한 회원 정보 반환
    @GetMapping("/me")
    public ResponseEntity<MemberDTO> getCurrentMember() {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(member);
    }
}
