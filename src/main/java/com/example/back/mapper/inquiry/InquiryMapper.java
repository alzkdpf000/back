package com.example.back.mapper.inquiry;

import com.example.back.dto.inquiry.InquiriesCountDto;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InquiryMapper {
//  검색 이메일 또는 아이디로 검색 결과 내림차순
    public List<InquiryMemberReplyDTO> selectInquiriesByEmailOrId(@Param("scrollCriteria") ScrollCriteria scrollCriteria, @Param("search")Search search);
//  답변 수, 미답변 수
    public InquiriesCountDto selectAnswerCounts (@Param("search")Search search);
//  문의글 상세보기
    public Optional<InquiryMemberReplyDTO> selectActiveInquiryWithReplyById(Long inquiriesId);
}
