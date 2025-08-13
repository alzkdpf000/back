package com.example.back.dto.inquiry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@EqualsAndHashCode(of = "id")
@Setter
@ToString
public class InquiryMemberReplyDTO {
    private Long id;
    private String inquiriesTitle;
    private String inquiriesContent;
    private String createdDateTime;
    private String createdDateTimeInquiry;
    private String memberEmail;
    private boolean hasAnswer;
    private String answerDatetime;
    private String answerDatetimeReply;
}
