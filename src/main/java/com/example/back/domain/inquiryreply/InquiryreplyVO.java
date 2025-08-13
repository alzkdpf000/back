package com.example.back.domain.inquiryreply;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@SuperBuilder
@Getter
@EqualsAndHashCode(of = "id")
public class InquiryreplyVO  extends Period {
    private Long id;
    private String inquiryReplyTitle;
    private String inquiryReplyContent;
    private Status inquiryReplyStatus;
    private Long memberId;
    private Long inquiryId;

}
