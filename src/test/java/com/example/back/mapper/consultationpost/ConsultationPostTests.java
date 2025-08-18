package com.example.back.mapper.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostCriteriaDTO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.service.consultationpost.ConsultationPostService;
import com.example.back.util.ScrollCriteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ConsultationPostTests {
    @Autowired
    ConsultationPostMapper consultationPostMapper;
    @Autowired
    ConsultationPostDAO consultationPostDAO;
    @Autowired
    ConsultationPostService consultationPostService;

    @Test
    public void testMapperSelectTop5OrderByViewCountDesc() {
        ScrollCriteria scrollCriteria = new ScrollCriteria(1);
        List<ConsultationPostCategoryFileUserDTO> consultationPostDTOS = consultationPostMapper.select5OrderByViewCountDesc(scrollCriteria);

        consultationPostDTOS.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);

    }
    @Test
    public void testDAOFindTop5OrderByViewCountDesc() {
        ScrollCriteria scrollCriteria = new ScrollCriteria(1);
        List<ConsultationPostCategoryFileUserDTO> consultationPostDTOS = consultationPostDAO.find5OrderByViewCountDesc(scrollCriteria);

        consultationPostDTOS.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
    }
    @Test
    public void testServiceGetTop5PostsByViews() {
        ScrollCriteria scrollCriteria = new ScrollCriteria(1);
        ConsultationPostCriteriaDTO consultationPostService5PostsByViews = consultationPostService.get5PostsByViews(1);
        log.info("{}", consultationPostService5PostsByViews);
    }
}
