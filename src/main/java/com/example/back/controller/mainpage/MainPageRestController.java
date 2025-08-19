package com.example.back.controller.mainpage;

import com.example.back.dto.consultationpost.ConsultationPostCriteriaDTO;
import com.example.back.service.consultationpost.ConsultationPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainPageRestController {
    private final ConsultationPostService consultationPostService;


    //  조회순으로 5개씩 무한 스크롤로 뿌려주기
    @GetMapping("{page}")
    public ResponseEntity<ConsultationPostCriteriaDTO> mainPage(@PathVariable int page) {
        ConsultationPostCriteriaDTO consultationPostCriteriaDTO = consultationPostService.get5PostsByViews(page);
        log.info("{}", consultationPostCriteriaDTO.getScrollCriteria().toString());
//        consultationPostService5PostsByViews.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
        if (consultationPostCriteriaDTO.getConsultationPosts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(consultationPostCriteriaDTO);
        }
        return ResponseEntity.ok(consultationPostCriteriaDTO);
    }
}
