package com.example.back.domain.membervisited;


import com.example.back.dto.membervisited.MemberVisitedDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class MemberVisitedVO extends MemberVisitedDTO {
    private Long id;
    private Long memberId;
    private String visitedDatetime;
}
