package com.example.back.mapper.inquiry;

import com.example.back.common.exception.InquiryNotFoundException;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.repository.inquiry.InquiryDAO;
import com.example.back.service.inquiry.InquiryService;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
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
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);
        String[] s = {};
        search.setCategories(s);
        ScrollCriteria scrollCriteria = new ScrollCriteria(search.getPage());
        log.info("testMapperSelectInquiriesByEmailOrId{}",inquiryMapper.selectInquiriesByEmailOrId(scrollCriteria,search));

    }
    @Test
    public void testDAOFindInquiriesByEmailOrId(){
        Search search = new Search();
        search.setPage(1);
        String[] s = {"0"};
        search.setCategories(s);
        ScrollCriteria scrollCriteria = new ScrollCriteria(search.getPage());
        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(scrollCriteria,search));
        log.info("{}",inquiryDAO.findInquiriesByEmailOrId(scrollCriteria,search).size());

    }

    @Test
    public void testMapperSelectAnswerCounts(){
        Search search = new Search();
        search.setPage(1);
        String[] s = {"0"};
        search.setCategories(s);
        log.info("{}",inquiryMapper.selectAnswerCounts(search));
    }
    @Test
    public void testDAOGetAnswerCounts(){
        Search search = new Search();
        search.setPage(1);
        String[] s = {"0"};
        search.setCategories(s);
        log.info("{}",inquiryDAO.getAnswerCounts(search));
    }

    @Test
    public void testServiceGetInquiryListWithAnswerStats(){
//        {keyword: keyword, page: page, categories:[]}
        ScrollCriteria scrollCriteria = new ScrollCriteria(1);
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);
        String[] s = {""};
        search.setCategories(s);
        log.info("testServiceGetInquiryListWithAnswerStats{}",inquiryService.getInquiryListWithAnswerStats(search));
    }

    @Test
    public void testMapperSelectActiveInquiryWithReplyById(){
        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryMapper.selectActiveInquiryWithReplyById(10L).orElseThrow(InquiryNotFoundException::new);
        log.info("{}",inquiryMemberReplyDTO);
    }
    @Test
    public void testDAOFindActiveInquiryWithReplyById(){
        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryDAO.findActiveInquiryWithReplyById(1L).orElseThrow(InquiryNotFoundException::new);
        log.info("{}",inquiryMemberReplyDTO);
    }

    @Test
    public void testServiceGetInquiryDetail(){
        InquiryMemberReplyDTO inquiryMemberReplyDTO = inquiryService.getInquiryDetail(1L).orElseThrow(InquiryNotFoundException::new);
        log.info("{}",inquiryMemberReplyDTO);
    }
}
