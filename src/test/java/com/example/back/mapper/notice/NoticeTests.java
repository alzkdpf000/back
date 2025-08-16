package com.example.back.mapper.notice;


import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.dto.notice.NoticesCriteriaDTO;
import com.example.back.repository.notice.NoticeDAO;
import com.example.back.service.notice.NoticeService;
import com.example.back.util.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class NoticeTests {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeDAO  noticeDAO;
    @Autowired
    private NoticeService noticeService;

    @Test
    public void testMapperSelectAll(){
        Criteria criteria = new Criteria(1,10);

        List<NoticeSummaryDTO> noticeSummaryDTOS = noticeMapper.selectAll(criteria);
        noticeSummaryDTOS.stream().map(NoticeSummaryDTO::toString).forEach(log::info);
    }

    @Test
    public void testDAOFindAll(){
        Criteria criteria = new Criteria(1,10);
        List<NoticeSummaryDTO> noticeSummaryDTOS = noticeDAO.findAll(criteria);
        noticeSummaryDTOS.stream().map(NoticeSummaryDTO::toString).forEach(log::info);
    }

    @Test
    public void testServiceGetList(){
        NoticesCriteriaDTO noticesCriteriaDTO = noticeService.getList(1);
        assertNotNull(noticesCriteriaDTO);
    }
    @Test
    public void testMapperSelectCountAll(){
        log.info("{}",noticeMapper.selectCountAll());
    }

    @Test
    public void testMapperFindCountAll(){
        log.info("{}",noticeDAO.findCountAll());
    }

    @Test
    public void testMapperSelectById(){
        log.info("{}",noticeMapper.selectById(180L));
    }
    @Test
    public void testDAOfindById(){
        log.info("{}",noticeDAO.findById(180L));
    }
    @Test
    public void testServiceGetNotice(){
        log.info("{}",noticeService.getNotice(180L));
    }
}
