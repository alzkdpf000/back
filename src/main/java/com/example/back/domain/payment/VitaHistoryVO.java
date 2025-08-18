package com.example.back.domain.payment;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Status;
import com.example.back.common.enumeration.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(of="id")
public class VitaHistoryVO extends Period {
    private Long id;
    private int vitaHistoryAmount;
    private Result vitaHistoryResult;
    private Type vitaHistoryType;
    private Status vitaHistoryStatus;
    private String vitaHistoryDescription;
    private Long memberId;
}
