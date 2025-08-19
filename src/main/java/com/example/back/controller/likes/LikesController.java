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
    public ResponseEntity<?> toggleLike(@RequestBody LikesDTO dto) {
        try {
            if (likesService.isLiked(dto)) {
                likesService.removeLike(dto);
                return ResponseEntity.ok("unliked");
            } else {
                likesService.addLike(dto);
                return ResponseEntity.ok("liked");
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