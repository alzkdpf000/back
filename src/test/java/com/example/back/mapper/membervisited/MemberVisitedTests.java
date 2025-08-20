package com.example.back.mapper.membervisited;

import com.example.back.dto.membervisited.MemberVisitedDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootTest
@Slf4j

public class MemberVisitedTests {
    @Autowired
    private  MemberVisitedMapper memberVisitedMapper;

    @Test
    public void testSelectMonthlyVisits(){
        log.info(memberVisitedMapper.selectMonthlyVisits().toString());
    }
    @Test
    public void testSelectCountTodayVisits(){
        log.info("{}",memberVisitedMapper.selectCountTodayVisits());
    }
}
