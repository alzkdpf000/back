package com.example.back.controller.memberfile;

import com.example.back.common.exception.LoginFailException;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.service.member.MemberService;
import com.example.back.service.memberfile.MemberFileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Slf4j
public class MemberFileRestController {

    private final MemberFileService memberFileService;
    private final MemberService memberService;

    @GetMapping("profile")
    public MemberProfileDTO getMemberFile(HttpSession session){
        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        회원정보가 없으면 예외 발생
        if (member == null) throw new LoginFailException();

        Long memberId = member.getId();
        return memberFileService.getMemberProfile(memberId);
    }

    @PostMapping("/profile/upload")
//    업로드한 파일들을 Multipaetfile로 받는다
    public ResponseEntity<MemberFileDTO> uploadProfile(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        log.info(file.getOriginalFilename());

        MemberDTO member = (MemberDTO) session.getAttribute("member");
        //        회원정보가 없으면 예외 발생
        if (member == null) throw new LoginFailException();

        MemberFileDTO fileInfo = memberFileService.update(member.getId(), file);

        return ResponseEntity.ok(fileInfo);
    }







}
