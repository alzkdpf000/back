package com.example.back.mapper.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.mapper.memberprofile.MemberProfileMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberFileMapperTests {
    @Autowired
     private MemberFileMapper memberFileMapper;
    @Autowired
    private MemberProfileMapper memberProfileMapper;

    @Test
    public void testGetMemberProfile(){
        log.info("{}",memberFileMapper.getMemberProfile(120L));
    }

    @Test
    public void testInsertFile(){
        MemberFileDTO memberFileDTO = new MemberFileDTO();
        memberFileDTO.setFileOriginalName("test");
        memberFileDTO.setFileName("test");
        memberFileDTO.setFileSize("123");
        memberFileDTO.setFilePath("test");

        memberFileMapper.insertFile(memberFileDTO);

        log.info("{}",memberFileMapper.getMemberProfile(120L));
    }

    @Test
    public void testInsertMemberFile(){
        Long memberId = 120L;
        Long fileId = 1L;

        memberFileMapper.insertMemberFile(memberId, fileId);
        MemberProfileDTO memberProfileDTO  = memberFileMapper.getMemberProfile(memberId);

        log.info("memberProfileDTO:{}",memberProfileDTO);

    }

    @Test
    public void testFindFileIdByMemberId(){
        log.info("{}",memberFileMapper.findFileIdByMemberId(1L));
    }

    @Test
    public void testDeleteMemberFile(){
        memberFileMapper.deleteMemberFile(1L);
    }



}
