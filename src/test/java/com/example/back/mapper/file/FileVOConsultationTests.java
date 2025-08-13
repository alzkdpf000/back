package com.example.back.mapper.file;

import com.example.back.dto.file.FileDTO;
import com.example.back.repository.file.FileConsultationPostDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
@Slf4j
public class FileVOConsultationTests {
    @Autowired
    FileConsultationPostMapper fileConsultationPostMapper;
    @Autowired
    FileConsultationPostDAO  fileConsultationPostDAO;
    @Test
    public void testMapperSelectFilesByPostId(){
        List<FileDTO> fileConsultationPostDTOS = fileConsultationPostMapper.selectFilesByPostId(2L);
        fileConsultationPostDTOS.stream().map(FileDTO::toString).forEach(log::info);
//        log.info("{}",fileConsultationPostDTOS);
    }

    @Test
    public void testDAOFindFilesByPostId(){
        List<FileDTO> fileConsultationPostDTOS = fileConsultationPostDAO.findFilesByPostId(1L);
        fileConsultationPostDTOS.stream().map(FileDTO::toString).forEach(log::info);
        log.info("{}",fileConsultationPostDTOS);
//        fileConsultationPostDTOS.forEach(log::info);
    }

    @Test
    public void testFile(){
        File file = new File("/Users/jeongchunghyo/Desktop/file/2025/08/13","t_test.jpeg");
        log.info("경로: {}", file.getAbsolutePath());
        log.info("존재 여부: {}", file.exists());
    }
}
