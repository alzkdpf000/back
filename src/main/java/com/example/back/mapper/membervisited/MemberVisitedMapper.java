package com.example.back.mapper.membervisited;

import com.example.back.domain.membervisited.MemberVisitedVO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.membervisited.MemberVisitedDTO;
import com.example.back.dto.membervisited.MemberVisitedStaticDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberVisitedMapper {
    // 월 별 방문자 수
    public List<MemberVisitedStaticDTO> selectMonthlyVisits();
    // 오늘 방문자 수
    public int selectCountTodayVisits();

    // 오늘 로그인 추가
    @Insert("insert into tbl_member_visited(member_id)" +
            "values(#{memberId})")
    public int insertMemberVisited(MemberVisitedVO memberVisitedVO);

//    오늘 로그인회원 조회
    public boolean selectMemberVisited();

}
