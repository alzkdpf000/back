package com.example.back.mapper.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemberFileMapper {

    //    회원 프로필 파일 가져오기
    public MemberProfileDTO getMemberProfile(Long memberId);

//    파일 추가
    public void insertFile(MemberFileDTO MemberFileDTO);

    // 회원파일 매핑
    public void insertMemberFile(@Param("memberId") Long memberId,
                          @Param("fileId") Long fileId);

//    파일 ID, 회원 ID에 파일 저장
    public void updateProfile(@Param("memberId") Long memberId,
                              @Param("fileId") Long fileId);

    // 특정 회원 프로필 파일 ID 가져오기
    public Long findFileIdByMemberId(Long memberId);

//    회원파일 매핑 제거
    public void deleteMemberFile(Long memberId);
//    파일 삭제
    public void deleteFileByMemberId(Long memberId);






}
