package com.example.back.mapper.membervisited;

import com.example.back.dto.membervisited.MemberVisitedDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberVisitedMapper {
    // 월 별 방문자 수
    public List<MemberVisitedDTO> selectMonthlyVisits();
    // 오늘 방문자 수
    public int selectCountTodayVisits();
}
