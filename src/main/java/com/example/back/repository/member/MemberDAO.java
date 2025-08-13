package com.example.back.repository.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

//    회원 추가
    public void save(MemberVO memberVO){
        memberMapper.insertMember(memberVO);
    }

//    이메일 검사
    public boolean isExistMemberEmail(String memberEmail){

        return memberMapper.existMemberEmail(memberEmail);
    }
}
