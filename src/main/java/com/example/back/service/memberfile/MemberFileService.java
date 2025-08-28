package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberProfileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface MemberFileService {


    // 회원이 가진 프로필 + 파일 조회
    public MemberProfileDTO getMemberProfile(Long memberId);

//    파일 업로드
    public void uploadProfile(Long memberId, MultipartFile file);





}

