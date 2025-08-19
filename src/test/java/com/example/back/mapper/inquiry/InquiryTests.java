package com.example.back.mapper.inquiry;

import com.example.back.common.exception.InquiryNotFoundException;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.repository.inquiry.InquiryDAO;
import com.example.back.service.inquiry.InquiryService;
import com.example.back.util.ScrollCriteria;
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
//    @Test
//    public void testMapperSelectInquiriesByEmailOrId(){
//        ScrollCriteria scrollCriteria = new ScrollCriteria(1,"","all");
//        log.info("testMapperSelectInquiriesByEmailOrId{}",inquiryMapper.selectInquiriesByEmailOrId(scrollCriteria));
//        log.info("testMapperSelectInquiriesByEmailOrId{}",inquiryMapper.selectInquiriesByEmailOrId(scrollCriteria));
//    }
//    @Test
//    public void testDAOFindInquiriesByEmailOrId(){
//        ScrollCriteria scrollCriteria = new ScrollCriteria(2,"","all");
//        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(scrollCriteria));
//        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(scrollCriteria));
//        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(scrollCriteria).size());
//
//    }
//
//    @Test
//    public void testMapperSelectAnswerCounts(){
//        log.info("{}",inquiryMapper.selectAnswerCounts());
//    }
//    @Test
//    public void testDAOGetAnswerCounts(){
//        log.info("{}",inquiryDAO.getAnswerCounts());
//    }
//
//    @Test
//    public void testServiceGetInquiryListWithAnswerStats(){
//        ScrollCriteria scrollCriteria = new ScrollCriteria(0,"","all");
//        log.info("testServiceGetInquiryListWithAnswerStats{}",inquiryService.getInquiryListWithAnswerStats(scrollCriteria));
//    }
//
//    @Test
//    public void testMapperSelectActiveInquiryWithReplyById(){
//        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryMapper.selectActiveInquiryWithReplyById(10L).orElseThrow(InquiryNotFoundException::new);
//        log.info("{}",inquiryMemberReplyDTO);
//    }
//    @Test
//    public void testDAOFindActiveInquiryWithReplyById(){
//        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryDAO.findActiveInquiryWithReplyById(1L).orElseThrow(InquiryNotFoundException::new);
//        log.info("{}",inquiryMemberReplyDTO);
//    }
//
//    @Test
//    public void testServiceGetInquiryDetail(){
//        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryService.getInquiryDetail(1L).orElseThrow(InquiryNotFoundException::new);
//        log.info("{}",inquiryMemberReplyDTO);
//    }
}
