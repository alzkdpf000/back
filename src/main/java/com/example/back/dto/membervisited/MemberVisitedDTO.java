package com.example.back.dto.membervisited;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Setter
public class MemberVisitedDTO {
    private Long id;
    private Long memberId;
    private String visitedDatetime;
    private String date;
    private String count;
}
