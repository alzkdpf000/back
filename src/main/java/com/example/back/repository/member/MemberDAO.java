package com.example.back.repository.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.mapper.member.MemberMapper;
import com.example.back.util.Criteria;
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

    //  회원 전체 수
    //  전체 개수 조회
    public int findCountAll() {
        return memberMapper.selectCountAll();

    }

    //  회원 정보 페이지 당
    public List<MemberDTO> findAll(Criteria criteria) {
        return memberMapper.selectMembers(criteria);
    }

    //  status 상관없이 회원 정보 가져오기
    public Optional<MemberDTO> findMemberByIdAllStatus(Long memberId) {
        return memberMapper.selectMemberByIdAllStatus(memberId);
    }
//  의사 가입 거절
    public int rejectDoctor(Long memberId) {
        return memberMapper.updateDoctorStatusToRejected(memberId);
    }
}
