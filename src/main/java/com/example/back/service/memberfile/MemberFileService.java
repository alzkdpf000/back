package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.mapper.member.MemberMapper;
import com.example.back.mapper.memberfile.MemberFileMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public interface MemberFileService {


    // 회원이 가진 프로필 + 파일 조회
    public MemberProfileDTO getMemberProfile(Long memberId);

    public void uploadProfile(Long memberId, MultipartFile file);





}

