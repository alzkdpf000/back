package com.example.back.controller.admin;

import com.example.back.common.exception.InquiryNotFoundException;
import com.example.back.common.exception.MemberNotFoundException;
import com.example.back.common.exception.NoticeNotFoundException;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.dto.inquiry.InquirySummaryDTO;
import com.example.back.dto.member.MemberCriteriaDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticesCriteriaDTO;
import com.example.back.service.inquiry.InquiryService;
import com.example.back.service.member.MemberService;
import com.example.back.service.notice.NoticeService;
import com.example.back.util.ScrollCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/**")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {
    private final InquiryService inquiryService;
    private final NoticeService noticeService;
    private final MemberService memberService;

    @GetMapping("inquires")
    public ResponseEntity<InquirySummaryDTO> inquires(@RequestParam int page,
                                                      @RequestParam String query,
                                                      @RequestParam String answerStatus) {
        log.info("{},{},{}",page,query,answerStatus);
        ScrollCriteria scrollCriteria = new ScrollCriteria(page,query,answerStatus);
        InquirySummaryDTO inquiryListWithAnswerStats = inquiryService.getInquiryListWithAnswerStats(scrollCriteria);
        return ResponseEntity.ok().body(inquiryListWithAnswerStats);
    }

    @GetMapping("inquires/{inquiryId}")
    public ResponseEntity<InquiryMemberReplyDTO> inquires(@PathVariable Long inquiryId) {
        Optional<InquiryMemberReplyDTO> inquiryDetail = inquiryService.getInquiryDetail(inquiryId);
        return ResponseEntity.ok().body(inquiryDetail.orElseThrow(InquiryNotFoundException::new));
    }
    @GetMapping("notices")
    public ResponseEntity<NoticesCriteriaDTO> notices(@RequestParam int page) {
        NoticesCriteriaDTO noticesCriteriaDTO = noticeService.getList(page);
        return ResponseEntity.ok().body(noticesCriteriaDTO);
    }
    @GetMapping("notices/{id}")
    public ResponseEntity<NoticeDTO> notices(@PathVariable Long id) {
        NoticeDTO notice = noticeService.getNotice(id).orElseThrow(NoticeNotFoundException::new);
        return ResponseEntity.ok().body(notice);
    }

    @GetMapping("members")
    public ResponseEntity<MemberCriteriaDTO> members(@RequestParam int page,
                                                     @RequestParam String query) {
        MemberCriteriaDTO members = memberService.getList(page);
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("members/{memberId}")
    public ResponseEntity<MemberDTO> members(@PathVariable Long memberId) {
        MemberDTO member = memberService.getMemberByIdAllStatus(memberId).orElseThrow(MemberNotFoundException::new);
        log.info("출력을 잘됨");
        return ResponseEntity.ok().body(member);
    }
}
