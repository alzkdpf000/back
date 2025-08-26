package com.example.back.mapper.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberFileMapper {

    //    회원 프로필 파일 가져오기
    public MemberProfileDTO findByMemberId(Long memberId);



}
