package com.example.back.mapper.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberFileMapper {

    //    회원 프로필 파일 가져오기
    public MemberProfileDTO getMemberProfile(Long memberId);

//    프로필 업로드
    public void uploadProfile(@Param("memberId") Long memberId,
                           @Param("fileName") String fileName,
                           @Param("filePath") String filePath,
                           @Param("originalName") String originalName,
                           @Param("contentType") String contentType,
                           @Param("fileSize") Long fileSize);



}
