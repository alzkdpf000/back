package com.example.back.mapper.review;

import com.example.back.dto.review.ReviewDTO;
import com.example.back.util.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ReviewMapperTests {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    public void selectReviewsByDoctorIdTest() {
        Long doctorId = 1L;
        Criteria criteria = new Criteria(1, 10);

        List<ReviewDTO> reviews = reviewMapper.selectReviewsByDoctorId(doctorId, criteria);
        log.info("조회된 리뷰 개수: {}", reviews.size());
        for (ReviewDTO review : reviews) {
            log.info("ReviewDTO = {}", review);
        }
    }

    @Test
    public void countReviewsByDoctorIdTest() {
        Long doctorId = 1L;

        int count = reviewMapper.countReviewsByDoctorId(doctorId);
        log.info("해당 의사 리뷰 총 개수: {}", count);
    }
}