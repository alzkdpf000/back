package com.example.back.mapper.inquiry;

import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.repository.inquiry.InquiryDAO;
import com.example.back.service.inquiry.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class InquiryTests {
    @Autowired
    private InquiryMapper inquiryMapper;
    @Autowired
    private InquiryDAO inquiryDAO;
    @Autowired
    private InquiryService inquiryService;
    @Test
    public void testMapperSelectInquiriesByEmailOrId(){
        log.info("testMapperSelectInquiriesByEmailOrId{}",inquiryMapper.selectInquiriesByEmailOrId("kakao","no",5));
        log.info("testMapperSelectInquiriesByEmailOrId{}",inquiryMapper.selectInquiriesByEmailOrId(null,"no",5));
    }
    @Test
    public void testDAOFindInquiriesByEmailOrId(){
        log.info("{}",inquiryDAO.findInquiriesByEmailOrId("kakao","yes",5));
        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(null,"yes",5));
        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(null,"all",5).size());

    }

    @Test
    public void testMapperSelectAnswerCounts(){
        log.info("{}",inquiryMapper.selectAnswerCounts());
    }
    @Test
    public void testDAOGetAnswerCounts(){
        log.info("{}",inquiryDAO.getAnswerCounts());
    }

    @Test
    public void testServiceGetInquiryListWithAnswerStats(){
        log.info("testServiceGetInquiryListWithAnswerStats{}",inquiryService.getInquiryListWithAnswerStats("kakao","all",5));
    }

    @Test
    public void testMapperSelectActiveInquiryWithReplyById(){
        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryMapper.selectActiveInquiryWithReplyById(10L).orElseThrow(RuntimeException::new);
        log.info("{}",inquiryMemberReplyDTO);
    }
    @Test
    public void testDAOFindActiveInquiryWithReplyById(){
        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryDAO.findActiveInquiryWithReplyById(1L).orElseThrow(RuntimeException::new);
        log.info("{}",inquiryMemberReplyDTO);
    }

    @Test
    public void testServiceGetInquiryDetail(){
        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryService.getInquiryDetail(1L).orElseThrow(RuntimeException::new);
        log.info("{}",inquiryMemberReplyDTO);
    }
}
