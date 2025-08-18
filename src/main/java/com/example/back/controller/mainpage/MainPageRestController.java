package com.example.back.controller.mainpage;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostCriteria;
import com.example.back.service.consultationpost.ConsultationPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainPageRestController {
    private final ConsultationPostService consultationPostService;


    //  조회순으로 5개씩 무한 스크롤로 뿌려주기
    @GetMapping("{page}")
    public ResponseEntity<ConsultationPostCriteria> mainPage(@PathVariable int page) {
        ConsultationPostCriteria consultationPostCriteria = consultationPostService.get5PostsByViews(page);
        log.info("{}",consultationPostCriteria.getScrollCriteria().toString());
//        consultationPostService5PostsByViews.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
        if (consultationPostCriteria.getConsultationPosts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(consultationPostCriteria);
        }
        return ResponseEntity.ok(consultationPostCriteria);
    }
}
