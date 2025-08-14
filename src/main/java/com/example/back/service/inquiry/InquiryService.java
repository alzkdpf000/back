package com.example.back.service.inquiry;


import com.example.back.domain.inquiry.InquiryVO;
import com.example.back.dto.inquiry.InquiryDTO;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.dto.inquiry.InquirySummaryDTO;

import java.util.List;
import java.util.Optional;

public interface InquiryService {
    //    문의글 목록
    public InquirySummaryDTO getInquiryListWithAnswerStats(String query,String answerStatus,int offset);
    //  문의글 상세보기
    public Optional<InquiryMemberReplyDTO> getInquiryDetail(Long inquiryId);

    default InquiryVO toVO(InquiryDTO inquiryDTO){
        return InquiryVO.builder()
                .id(inquiryDTO.getId())
                .inquiryTitle(inquiryDTO.getInquiryTitle())
                .inquiryContent(inquiryDTO.getInquiryContent())
                .inquiryViewCount(inquiryDTO.getInquiryViewCount())
                .inquiryStatus(inquiryDTO.getInquiryStatus())
                .memberId(inquiryDTO.getMemberId())
                .createdDatetime(inquiryDTO.getCreatedDatetime())
                .updatedDatetime(inquiryDTO.getUpdatedDatetime())
                .build();
    }

}
