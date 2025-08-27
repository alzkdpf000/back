package com.example.back.mapper.memberprofile;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberProfileMapperTests {
    @Autowired
    private MemberProfileMapper memberProfileMapper;


    @Test
    public void testSelectByFileId() {
        memberProfileMapper.deleteByFileId(1L);

    }
}
