package com.example.back.dto.vitahistory;

import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Status;
import com.example.back.common.enumeration.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class VitaHistoryDTO {
    private Long id;
    private int vitaHistoryAmount;
    private Result vitaHistoryResult;
    private Type vitaHistoryType;
    private Status vitaHistoryStatus;
    private String vitaHistoryDescription;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;
    private String memberName;
    private Long paymentId;
    private String memberEmail;
    private String memberProvider;
    private String memberKakaoEmail;
}
