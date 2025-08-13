package com.example.back.mapper.inquiry;

import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    public List<InquiryMemberReplyDTO> selectInquiriesByEmailOrId(String query);
}
