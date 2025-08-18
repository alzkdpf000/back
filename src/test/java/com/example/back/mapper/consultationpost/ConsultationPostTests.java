//package com.example.back.mapper.consultationpost;
//
//import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
//import com.example.back.repository.consultationpost.ConsultationPostDAO;
//import com.example.back.service.consultationpost.ConsultationPostService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class ConsultationPostTests {
//    @Autowired
//    ConsultationPostMapper consultationPostMapper;
//    @Autowired
//    ConsultationPostDAO consultationPostDAO;
//    @Autowired
//    ConsultationPostService consultationPostService;
//
//    @Test
//    public void testMapperSelectTop5OrderByViewCountDesc() {
//        List<ConsultationPostCategoryFileUserDTO> consultationPostDTOS = consultationPostMapper.select5OrderByViewCountDesc(5);
//
//        consultationPostDTOS.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
//
//    }
//    @Test
//    public void testDAOFindTop5OrderByViewCountDesc() {
//        List<ConsultationPostCategoryFileUserDTO> consultationPostDTOS = consultationPostDAO.find5OrderByViewCountDesc(5);
//
//        consultationPostDTOS.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
//    }
//    @Test
//    public void testServiceGetTop5PostsByViews() {
//        List<ConsultationPostCategoryFileUserDTO> consultationPostDTOS = consultationPostService.get5PostsByViews(5);
//
//        consultationPostDTOS.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
//    }
//}
