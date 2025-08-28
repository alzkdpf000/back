package com.example.back.controller.review;

import com.example.back.dto.review.ReviewDTO;
import com.example.back.service.houseCall.HouseCallService;
import com.example.back.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewRestController {

    private final HouseCallService houseCallService;
    private final ReviewService reviewService;

//  로그인한 멤버가 해당 의사의 방문진료 기록이 있는지 확인
    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkReviewEligibility(
            @RequestParam Long doctorId,
            @RequestParam Long memberId) {

        Map<String, Boolean> result = new HashMap<>();
        boolean hasVisited = false;
        try {
            hasVisited = houseCallService.hasVisitedDoctor(memberId, doctorId);
        } catch (Exception e) {
            System.err.println("API 호출 중 오류: " + e.getMessage());
        }
        System.out.println("memberId=" + memberId + ", doctorId=" + doctorId + ", hasVisited=" + hasVisited);
        result.put("hasVisited", hasVisited);
        return ResponseEntity.ok(result);
    }

//  해당 의사의 리뷰가 존재하는지 확인
    @GetMapping("/exists")
    public ResponseEntity<Map<String, Boolean>> existsReview(
            @RequestParam Long doctorId,
            @RequestParam Long memberId) {

        boolean exists = reviewService.hasExistingReview(memberId, doctorId);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);

        return ResponseEntity.ok(result);
    }

    //  후기 작성시 DB에 저장
    @PostMapping("/insert")
    public ResponseEntity<String> insertReview(@RequestBody ReviewDTO reviewDTO) {
        boolean exists = reviewService.hasExistingReview(reviewDTO.getMemberId(), reviewDTO.getDoctorId());
        if (exists) {
            return ResponseEntity
                    .badRequest()
                    .body("이미 리뷰를 작성하셨습니다.");
        }

        reviewService.insertReview(reviewDTO);
        return ResponseEntity.ok().build();
    }

}
