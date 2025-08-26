package com.example.back.mapper.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberFileMapper {

    //    회원 프로필 파일 가져오기
    public MemberFileDTO findByMemberId(@Param("memberId") Long memberId);



}
