package com.example.back.repository.review;

import com.example.back.dto.review.ReviewDTO;
import com.example.back.mapper.counselreply.CounselReplyMapper;
import com.example.back.mapper.review.ReviewMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {

    private final ReviewMapper reviewMapper;

    // 리뷰 삽입
    public void insertReview(ReviewDTO reviewDTO) {
        reviewMapper.insertReview(reviewDTO);
    }

    //  해당 의사의 리뷰 목록 조회
    public List<ReviewDTO> findReviewsByDoctorId(Long doctorId, Criteria criteria) {
        return reviewMapper.selectReviewsByDoctorId(doctorId, criteria);
    }

    //  해당 의사의 리뷰 총 개수 조회
    public int countReviewsByDoctorId(Long doctorId) {
        return reviewMapper.countReviewsByDoctorId(doctorId);
    }

    // 현재 멤버가 해당 의사에게 작성한 리뷰 존재 여부
    public boolean hasExistingReview(Long memberId, Long doctorId) {
        return reviewMapper.existsReviewByMemberAndDoctor(memberId, doctorId) > 0;
    }

}
