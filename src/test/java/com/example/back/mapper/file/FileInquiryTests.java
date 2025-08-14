package com.example.back.mapper.file;

import com.example.back.repository.file.FileInquiryDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileInquiryTests {
    @Autowired
    private FileInquiryMapper fileInquiryMapper;
    @Autowired
    private FileInquiryDAO fileInquiryDAO;

    @Test
    public void testMapperSelectFilesByInquiryId(){
        log.info("{}",fileInquiryMapper.selectFilesByInquiryId(1L));
    }

    @Test
    public void testDAOFindFilesByInquiryId(){
        log.info("{}",fileInquiryDAO.findFilesByInquiryId(1L));
    }
}
