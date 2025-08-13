package com.example.back.dto.inquiryreply;

import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @EqualsAndHashCode(of = "id")
@Setter @ToString
public class InquiryReplyDTO {
    private Long id;
    private String inquiryReplyTitle;
    private String inquiryReplyContent;
    private Status inquiryReplyStatus;
    private Long memberId;
    private Long inquiryId;
    private String createdDatetime;
    private String updatedDatetime;
}
