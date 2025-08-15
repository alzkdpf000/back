package com.example.back.dto.inquiry;

import com.example.back.util.ScrollCriteria;
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
public class InquirySummaryDTO {
    private List<InquiryMemberReplyDTO> inquiryMemberReplyDTOs;
    private InquiriesCountDto inquiriesCountDto;
    private ScrollCriteria scrollCriteria;
}
