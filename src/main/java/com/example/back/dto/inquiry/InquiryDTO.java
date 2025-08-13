package com.example.back.dto.inquiry;

import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @EqualsAndHashCode(of = "id")
@Setter @ToString
public class InquiryDTO {
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;
    private Status inquiryStatus;
    private int inquiryViewCount;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;
}
