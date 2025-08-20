package com.example.back.dto.member;

import com.example.back.dto.membervisited.MemberVisitedDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Setter
public class MemberAdminStatics {
    public List<MemberStatics> monthlyJoins;
    public List<MemberVisitedDTO> monthlyVisited;
    public int todayJoin;
    public int todayVisited;
}
