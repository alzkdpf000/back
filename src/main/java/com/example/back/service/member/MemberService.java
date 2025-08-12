package com.example.back.service.member;


import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Role;
import com.example.back.common.enumeration.Status;
import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;

public interface MemberService {

//    회원가입
    public void join(MemberDTO memberDTO);

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
                .createdDate(memberDTO.getCreatedDatetime())
                .updatedDate(memberDTO.getUpdatedDatetime())
                .build();

    }

}
