package com.example.back.dto.inquiry;

import com.example.back.dto.file.FileDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@EqualsAndHashCode(of = "id")
@Setter
@ToString
public class InquiryMemberReplyDTO {
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;
    private String createdDateTime;
    private String createdDateTimeInquiry;
    private String memberEmail;
    private String inquiryReplyContent;
    private boolean hasAnswer;
    private String answerDatetime;
    private String answerDatetimeReply;
    List<FileDTO> files;

}
