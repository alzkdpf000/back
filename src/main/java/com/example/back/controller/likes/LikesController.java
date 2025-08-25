package com.example.back.controller.likes;

import com.example.back.dto.likes.LikesDTO;
import com.example.back.service.likes.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody LikesDTO dto) {
        try {
            boolean alreadyLiked = likesService.isLiked(dto); // DB에서 실제 체크
            if (alreadyLiked) {
                likesService.removeLike(dto); // 좋아요 취소
                return ResponseEntity.ok("unliked"); // 정확히 unliked 반환
            } else {
                likesService.addLike(dto); // 좋아요 추가
                return ResponseEntity.ok("liked"); // liked 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("좋아요 등록/삭제 실패");
        }
    }

    @GetMapping("/count/{doctorId}")
    public ResponseEntity<Integer> getLikesCount(@PathVariable Long doctorId) {
        try {
            int count = likesService.getLikesCount(doctorId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(0);
        }
    }

}