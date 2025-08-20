package com.example.back.repository.membervisited;

import com.example.back.dto.membervisited.MemberVisitedDTO;
import com.example.back.dto.membervisited.MemberVisitedStaticDTO;
import com.example.back.mapper.membervisited.MemberVisitedMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberVisitedDAO {
    private final MemberVisitedMapper memberVisitedMapper;
    // 월 별 방문자 수
    public List<MemberVisitedStaticDTO> findMonthlyVisits(){
        return memberVisitedMapper.selectMonthlyVisits();
    }
    // 오늘 방문자 수
    public int findCountTodayVisits(){
        return memberVisitedMapper.selectCountTodayVisits();
    }
}
