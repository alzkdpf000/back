package com.example.back.mapper.member;

import com.example.back.domain.member.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testInsertMember(){
        MemberVO member = MemberVO.builder()
                .memberEmail("test@gmail.com")
                .memberName("test")
                .memberPhone("010-0000-0000")
                .memberPassword("1234")
                .build();

        memberMapper.insertMember(member);

    }

    @Test
    public void testExistMemberEmail(){
        boolean isExist = memberMapper.existMemberEmail("test@gmail.com");
        log.info("isExist: {}", isExist);
    }

}
