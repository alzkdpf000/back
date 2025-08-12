package com.example.back.mapper.file;

import com.example.back.repository.file.FileConsultationPostDAO;
import com.example.back.service.file.FileConsultationPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FileConsultationTests {
    @Autowired
    FileConsultationPostMapper fileConsultationPostMapper;
    @Autowired
    FileConsultationPostDAO  fileConsultationPostDAO;
    @Autowired
    FileConsultationPostService fileConsultationPostService;
    @Test
    public void testMapperSelectFilesByPostId(){
        List<String> fileConsultationPostDTOS = fileConsultationPostMapper.selectFilesByPostId(2L);
        fileConsultationPostDTOS.forEach(log::info);
    }

    @Test
    public void testDAOFindFilesByPostId(){
        List<String> fileConsultationPostDTOS = fileConsultationPostDAO.findFilesByPostId(1L);
        fileConsultationPostDTOS.forEach(log::info);
    }
    @Test
    public void testServiceGetFilesByPostId(){
        List<String> fileConsultationPostDTOS = fileConsultationPostService.getFilesByPostId(1L);
        fileConsultationPostDTOS.forEach(log::info);
    }
}
