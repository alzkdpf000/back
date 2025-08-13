package com.example.back.repository.inquiry;

import com.example.back.common.enumeration.Status;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.mapper.inquiry.InquiryMapper;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryDAO {
    private final InquiryMapper inquiryMapper;

    List<InquiryMemberReplyDTO> findInquiriesByEmailOrId(String query){
        return inquiryMapper.selectInquiriesByEmailOrId(query);
    }
}
