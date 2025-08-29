package com.example.back.service.member;


import com.example.back.domain.member.MemberVO;
import com.example.back.domain.membervisited.MemberVisitedVO;
import com.example.back.dto.member.MemberAdminStatics;
import com.example.back.dto.member.MemberCriteriaDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.util.Search;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public interface MemberService {

//    회원가입
    public MemberDTO join(MemberDTO memberDTO);

//    이메일 검사
    public boolean isExistMemberEmail(String memberEmail);

//   관리자 페이지 유저 목록들 가져오기
    public MemberCriteriaDTO getListAllStatus(Search search);
    //  status 상관없이 회원 정보 가져오기
    public Optional<MemberDTO> getMemberByIdAllStatus(Long memberId);

    //   의사 가입 거절(회원 상태 자체를 inactive로)
    public boolean reject(Long memberId);

//    로그인
    public Optional<MemberDTO> login(MemberDTO memberDTO);

//    관리자 페이지 회원들 통계 자료들
    public MemberAdminStatics getStatics();

//    로그인 추가
    public int insertMemberVisited(MemberVisitedVO memberVisitedVO);

//    로그인 추가 조회
    public boolean selectMemberVisited(Long memberId);

//    카카오 기존 회원정보 조회
    public Optional<MemberDTO> getKakaoMember(String kakaoEmail);

//    카카오 회원가입
    public void joinKakaoMember(MemberDTO memberDTO);

//    비밀번호 재설정
    public void updatePassword(String memberEmail, String memberPassword);




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
                .KakaoEmail(memberDTO.getKakaoEmail())
                .KakaoProfileUrl(memberDTO.getKakaoProfileUrl())
                .provider(memberDTO.getProvider())
                .role(memberDTO.getMemberRole())
                .memberVitaAmount(memberDTO.getMemberVitaAmount())
                .createdDatetime(memberDTO.getCreatedDatetime())
                .updatedDatetime(memberDTO.getUpdatedDatetime())
                .build();

    }
    default String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
