package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface MemberFileService {


    // 회원이 가진 프로필 + 파일 조회
    public MemberProfileDTO getMemberProfile(Long memberId);



//    프로필 수정
    public MemberFileDTO update(Long memberId, MultipartFile file);

    default String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}

