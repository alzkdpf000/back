package com.example.back.controller.review;

import com.example.back.service.houseCall.HouseCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final HouseCallService houseCallService;

    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkReviewEligibility(
            @RequestParam Long doctorId,
            @RequestParam Long memberId) {

        boolean isLoggedIn = memberId != null; // 간단 체크, 실제로는 세션/토큰 검사
        boolean hasVisited = false;

        if (isLoggedIn) {
            hasVisited = houseCallService.hasVisitedDoctor(memberId, doctorId);
        }

        Map<String, Boolean> result = new HashMap<>();
        result.put("isLoggedIn", isLoggedIn);
        result.put("hasVisited", hasVisited);

        return ResponseEntity.ok(result);
    }
}
