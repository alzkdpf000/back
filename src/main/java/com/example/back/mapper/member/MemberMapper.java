package com.example.back.mapper.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.member.MemberStatics;
import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    //    회원 가입 추가
    public void insertMember(MemberVO memberVO);
//    카카오 회원가입 추가
    public void insertKakaoMember(MemberVO memberVO);

    //    이메일 검사
    @Select("select count(*) > 0 " +
            "from tbl_member " +
            "where member_email = #{memberEmail}")
    public boolean existMemberEmail(String memberEmail);

    //  회원 전체 수
    public int selectCountAllStatus(@Param("search") Search search);

    //  회원 정보 페이지 당
    public List<MemberDTO> selectMembers(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //  status 상관없이 회원 정보 가져오기
    public Optional<MemberDTO> selectMemberByIdAllStatus(Long memberId);

    //   의사 가입 거절(회원 상태 자체를 inactive로)
    public int updateDoctorStatusToRejected(Long memberId);

//    로그인
    public Optional<MemberDTO> selectMemberForLogin(MemberDTO memberDTO);

    //  월 별 가입자 수
    public List<MemberStatics> selectMonthlyJoin();

    //  오늘 가입자 수
    public int selectCountTodayJoin();

//    카카오 로그인 - 최초 로그인인지 확인
    public Optional<MemberDTO> selectMemberForKakaoEmail(String kakaoEmail);

//    비밀번호 재설정
    public void updatePassword(@Param("memberEmail") String memberEmail, @Param("memberPassword") String memberPassword);

//    회원 프로필 파일 가져오기
    public List<MemberFileDTO> findByMemberId(@Param("memberId") Long memberId);
}
