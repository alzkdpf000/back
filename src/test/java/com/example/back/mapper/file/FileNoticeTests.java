package com.example.back.mapper.file;

import com.example.back.repository.file.FileNoticeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileNoticeTests {
    @Autowired
    private FileNoticeMapper fileNoticeMapper;
    @Autowired
    private FileNoticeDAO fileNoticeDAO;

    @Test
    public void testSelectFilesByNoticeId(){
        log.info("test{}", fileNoticeMapper.selectFilesByNoticeId(180L));
    }

    @Test
    public void testSelectFindFilesByNoticeId(){
        log.info("test{}", fileNoticeDAO.findFilesByNoticeId(180L));
    }
}
