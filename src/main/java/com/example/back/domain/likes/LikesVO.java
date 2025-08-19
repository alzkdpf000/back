package com.example.back.domain.likes;

import com.example.back.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(of = "id")
public class LikesVO extends Period {
    private Long id;
    private Long memberId;
    private Long doctorId;
}