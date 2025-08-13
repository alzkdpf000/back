package com.example.back.repository.inquiry;

import com.example.back.common.enumeration.Status;
import com.example.back.dto.inquiry.InquiriesCountDto;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.mapper.inquiry.InquiryMapper;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
@RequiredArgsConstructor
public class InquiryDAO {
    private final InquiryMapper inquiryMapper;
    //  검색 이메일 또는 아이디로 검색 결과 내림차순
    public List<InquiryMemberReplyDTO> findInquiriesByEmailOrId(String query,boolean answerStatus){
        return inquiryMapper.selectInquiriesByEmailOrId(query,answerStatus);
    }
    //  답변 수, 미답변 수
    public InquiriesCountDto getAnswerCounts(){
        return inquiryMapper.selectAnswerCounts();
    }
    //  문의글 상세보기
    public Optional<InquiryMemberReplyDTO> findActiveInquiryWithReplyById(Long inquiriesId){
        return inquiryMapper.selectActiveInquiryWithReplyById(inquiriesId);
    }
}
