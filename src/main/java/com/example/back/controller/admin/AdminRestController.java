package com.example.back.controller.admin;

import com.example.back.dto.inquiry.InquirySummaryDTO;
import com.example.back.service.inquiry.InquiryService;
import com.example.back.util.ScrollCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/**")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {
    private final InquiryService inquiryService;

    @GetMapping("inquires/{page}")
    public ResponseEntity<InquirySummaryDTO> inquires(@PathVariable int page,
                                                      @RequestParam String query,
                                                      @RequestParam String answerStatus) {
        log.info("{},{},{}",page,query,answerStatus);
        ScrollCriteria scrollCriteria = new ScrollCriteria(page,query,answerStatus);
        InquirySummaryDTO inquiryListWithAnswerStats = inquiryService.getInquiryListWithAnswerStats(scrollCriteria);
        return ResponseEntity.ok().body(inquiryListWithAnswerStats);
    }
}
