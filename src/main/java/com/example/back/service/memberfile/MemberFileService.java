package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.mapper.member.MemberMapper;
import com.example.back.mapper.memberfile.MemberFileMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberFileService {
    private final MemberFileMapper memberFileMapper;

    //    회원이 가진 파일 조회
    public MemberFileDTO getMemberFile(Long memberId){
        return memberFileMapper.findByMemberId(memberId);

    }

}
