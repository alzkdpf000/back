package com.example.back.service.review;

import com.example.back.domain.review.ReviewVO;
import com.example.back.dto.review.ReviewDTO;
import com.example.back.util.Criteria;

import java.util.List;

public interface ReviewService {


    // 해당 의사의 리뷰 목록 조회 (페이징 포함)
    List<ReviewDTO> getReviewsByDoctorId(Long doctorId, Criteria criteria);

    // 해당 의사의 리뷰 총 개수 조회
    int getReviewCountByDoctorId(Long doctorId);

//    // 리뷰 작성
//    void writeReview(ReviewDTO reviewDTO);
//
//    // 리뷰 수정
//    void updateReview(ReviewDTO reviewDTO);
//
//    // 리뷰 삭제 (상태값 변경)
//    void deleteReview(Long reviewId);


    default ReviewVO toVO(ReviewDTO reviewDTO) {
        return ReviewVO.builder()
                .id(reviewDTO.getId())
                .doctorId(reviewDTO.getDoctorId())
                .memberId(reviewDTO.getMemberId())
                .rating(reviewDTO.getRating())
                .content(reviewDTO.getContent())
                .build();
    }
}
