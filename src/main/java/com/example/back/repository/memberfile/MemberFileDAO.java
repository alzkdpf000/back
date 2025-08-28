package com.example.back.repository.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.mapper.memberfile.MemberFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberFileDAO {
    private final MemberFileMapper memberFileMapper;

//    회원 프로필 파일 가져오기
    public MemberProfileDTO getMemberProfile(Long memberId){
        return memberFileMapper.getMemberProfile(memberId);
    }
//    기본 프로필 설정
    public void insertFile(MemberFileDTO memberFileDTO) {
        memberFileMapper.insertFile(memberFileDTO);
    }
//    회원 ID랑 파일 ID에 저장
    public void insertMemberFile (Long memberId, Long fileId) {
        memberFileMapper.insertMemberFile(memberId, fileId);
    }
//     회원 ID에 연결
    public void updateProfile(Long memberId, Long fileId) {
        memberFileMapper.updateProfile(memberId, fileId);
    }

//    첨부파일 삭제
    public void deleteFileByMemberId(Long memberId) {
        memberFileMapper.deleteFileByMemberId(memberId);
    }

    public void deleteMemberFile(Long memberId) {
        memberFileMapper.deleteMemberFile(memberId);
    }
}
