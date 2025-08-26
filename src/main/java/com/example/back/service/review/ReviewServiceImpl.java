package com.example.back.service.review;

import com.example.back.repository.review.ReviewDAO;
import com.example.back.dto.review.ReviewDTO;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;

    @Override
    public List<ReviewDTO> getReviewsByDoctorId(Long doctorId, Criteria criteria) {
        return reviewDAO.findReviewsByDoctorId(doctorId, criteria);
    }

    @Override
    public int getReviewCountByDoctorId(Long doctorId) {
        return reviewDAO.countReviewsByDoctorId(doctorId);
    }

    @Override
    public boolean hasExistingReview(Long memberId, Long doctorId) {
        return reviewDAO.hasExistingReview(memberId, doctorId);
    }

//  후기 등록
    @Override
    public void insertReview(ReviewDTO reviewDTO) {
        reviewDAO.insertReview(reviewDTO); 
    }

//    @Override
//    public void writeReview(ReviewDTO reviewDTO) {
//        // 방문 진료 여부 확인
//        int exists = reviewDAO.existsHouseCallByMemberAndDoctor(
//                reviewDTO.getMemberId(), reviewDTO.getDoctorId()
//        );
//
//        if (exists == 0) {
//            throw new IllegalStateException("해당 의사에게 방문 진료 이력이 없어 리뷰 작성이 불가합니다.");
//        }
//
//        reviewDAO.save(reviewDTO);
//    }
//
//    @Override
//    public void updateReview(ReviewDTO reviewDTO) {
//        reviewDAO.update(reviewDTO);
//    }
//
//    @Override
//    public void deleteReview(Long reviewId) {
//        reviewDAO.delete(reviewId);
//    }
}