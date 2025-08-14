package com.example.back.controller.admin;

import com.example.back.dto.inquiry.InquirySummaryDTO;
import com.example.back.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/**")
@RequiredArgsConstructor
public class AdminRestController {
private final InquiryService inquiryService;
    @GetMapping("inquires")
    public ResponseEntity<InquirySummaryDTO> inquires(@RequestParam int offset,@RequestParam String query,@RequestParam String answerStatus){
        InquirySummaryDTO inquiryListWithAnswerStats = inquiryService.getInquiryListWithAnswerStats(query, answerStatus, offset);
        return ResponseEntity.ok().body(inquiryListWithAnswerStats);
    }
}
