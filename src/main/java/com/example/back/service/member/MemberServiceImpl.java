package com.example.back.service.member;

import com.example.back.dto.member.MemberDTO;
import com.example.back.repository.memberDAO.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService{

    private final MemberDAO memberDAO;

    @Override
    public void join(MemberDTO memberDTO) {
        memberDAO.save(toVO(memberDTO));
    }

}
