package com.example.back.mapper.member;

import com.example.back.domain.member.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
//    회원 가입 추가
    public void insertMember(MemberVO memberVO);
}
