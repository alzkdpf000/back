package com.example.back.mapper.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원 가입 추가
    public void insertMember(MemberVO memberVO);

//    이메일 검사
    @Select("select count(*) > 0 " +
            "from tbl_member " +
            "where member_email = #{memberEmail}")
    public boolean existMemberEmail(String memberEmail);

//    로그인 (조회)
    public Optional<MemberDTO> selectMemberForLogin(MemberDTO memberDTO);

}
