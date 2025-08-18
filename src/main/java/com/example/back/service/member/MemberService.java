package com.example.back.service.member;


import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberCriteriaDTO;
import com.example.back.dto.member.MemberDTO;

import java.util.Optional;

public interface MemberService {

//    회원가입
    public void join(MemberDTO memberDTO);

//    이메일 검사
    public boolean isExistMemberEmail(String memberEmail);

//   관리자 페이지 유저 목록들 가져오기
    public MemberCriteriaDTO getList(int page);
    //  status 상관없이 회원 정보 가져오기
    public Optional<MemberDTO> getMemberByIdAllStatus(Long memberId);

    //   의사 가입 거절
    public boolean reject(Long memberId);

//    회원가입 유효성 검사
    default boolean validateMember(MemberDTO memberDTO){
        if(memberDTO.getMemberName()==null||memberDTO.getMemberName().isBlank()){
            return false;
        }
        if (memberDTO.getMemberEmail()==null||memberDTO.getMemberEmail().isBlank()){
            return false;
        }

        String emailPattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (!memberDTO.getMemberEmail().matches(emailPattern)){
            return false;
        }

        String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        if (!memberDTO.getMemberPassword().matches(pwPattern)){
            return false;
        }

        return true;

    }
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
