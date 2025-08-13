package com.example.back.mapper.inquiry;

import com.example.back.dto.inquiry.InquiriesCountDto;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InquiryMapper {
//  검색 이메일 또는 아이디로 검색 결과 내림차순
    public List<InquiryMemberReplyDTO> selectInquiriesByEmailOrId(String query,boolean answerStatus);
//  답변 수, 미답변 수
    public InquiriesCountDto selectAnswerCounts ();
//  문의글 상세보기
    public Optional<InquiryMemberReplyDTO> selectActiveInquiryWithReplyById(Long inquiriesId);
}
