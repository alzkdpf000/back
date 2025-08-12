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
    MemberMapper memberMapper;

    @Test
    public void testInsertMember(){
        MemberVO member = MemberVO.builder()
                .memberEmail("test@gmail.com");

    }

}
