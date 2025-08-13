package com.example.back.repository.memberDAO;

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
}
