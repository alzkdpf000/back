package com.example.back.domain.inquiry;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@SuperBuilder
@Getter @EqualsAndHashCode(of = "id")
public class InquiryVO extends Period {
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;
    private Status inquiryStatus;
    private int inquiryViewCount;
    private Long memberId;
}
