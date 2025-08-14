package com.example.back.service.member;


import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;

public interface MemberService {

//    회원가입
    public void join(MemberDTO memberDTO);

//    이메일 검사
    public boolean isExistMemberEmail(String memberEmail);
    
    default MemberVO toVO(MemberDTO memberDTO){
        return MemberVO.builder()
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberName(memberDTO.getMemberName())
                .memberPhone(memberDTO.getMemberPhone())
                .memberStatus(memberDTO.getMemberStatus())
                .provider(memberDTO.getProvider())
                .role(memberDTO.getRole())
                .memberVitaAmount(memberDTO.getMemberVitaAmount())
                .createdDatetime(memberDTO.getCreatedDatetime())
                .updatedDatetime(memberDTO.getUpdatedDatetime())
                .build();

    }

}
