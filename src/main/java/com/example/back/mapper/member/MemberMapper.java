package com.example.back.mapper.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
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

//  회원 전체 수
    public int selectCountAll();
//  회원 정보 페이지 당
    public List<MemberDTO> selectMembers(@Param("criteria")Criteria criteria);

//  status 상관없이 회원 정보 가져오기
    public Optional<MemberDTO> selectMemberByIdAllStatus(Long memberId);
}
