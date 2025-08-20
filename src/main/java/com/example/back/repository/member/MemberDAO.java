package com.example.back.repository.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.member.MemberStatics;
import com.example.back.mapper.member.MemberMapper;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    //    회원 추가
    public void save(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }

    //    이메일 검사
    public boolean isExistMemberEmail(String memberEmail) {

        return memberMapper.existMemberEmail(memberEmail);
    }

//    로그인
    public Optional<MemberDTO> findMemberEmailAndPassword(MemberDTO memberDTO) {
        return memberMapper.selectMemberForLogin(memberDTO);
    }

    //  회원 전체 수
    //  전체 개수 조회
    public int findCountAllStatus(Search search) {
        return memberMapper.selectCountAllStatus(search);

    }

    //  회원 정보 페이지 당
    public List<MemberDTO> findAll(Criteria criteria, Search search) {
        return memberMapper.selectMembers(criteria, search);
    }

    //  status 상관없이 회원 정보 가져오기
    public Optional<MemberDTO> findMemberByIdAllStatus(Long memberId) {
        return memberMapper.selectMemberByIdAllStatus(memberId);
    }

    //   의사 가입 거절(회원 상태 자체를 inactive로)
    public int rejectDoctor(Long memberId) {
        return memberMapper.updateDoctorStatusToRejected(memberId);
    }

    //  월 별 가입자 수
    public List<MemberStatics> findMonthlyJoin() {
        return memberMapper.selectMonthlyJoin();
    }

    //  오늘 가입자 수
    public int findCountTodayJoin() {
        return memberMapper.selectCountTodayJoin();
    }
}
