package com.example.back.controller.admin;

import com.example.back.common.enumeration.Status;
import com.example.back.common.exception.DoctorNotFoundException;
import com.example.back.common.exception.InquiryNotFoundException;
import com.example.back.common.exception.MemberNotFoundException;
import com.example.back.common.exception.NoticeNotFoundException;
import com.example.back.dto.doctor.DoctorCriteriaDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.dto.inquiry.InquirySummaryDTO;
import com.example.back.dto.member.MemberCriteriaDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticesCriteriaDTO;
import com.example.back.dto.payment.PaymentCriteriaDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.service.inquiry.InquiryService;
import com.example.back.service.member.MemberService;
import com.example.back.service.notice.NoticeService;
import com.example.back.service.payment.PaymentService;
import com.example.back.service.vitahistory.VitaHistoryService;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final PaymentService paymentService;
    private final DoctorService doctorService;

    @PostMapping("inquires")
    public ResponseEntity<InquirySummaryDTO> searchInquiries(@RequestBody Search search) {
        log.info("search ::::::::::::{}",search.toString());
        ;
        InquirySummaryDTO inquiryListWithAnswerStats = inquiryService.getInquiryListWithAnswerStats(search);
        return ResponseEntity.ok().body(inquiryListWithAnswerStats);
    }

    @GetMapping("inquires/{inquiryId}")
    public ResponseEntity<InquiryMemberReplyDTO> inquiryDetail(@PathVariable Long inquiryId) {
        Optional<InquiryMemberReplyDTO> inquiryDetail = inquiryService.getInquiryDetail(inquiryId);
        return ResponseEntity.ok().body(inquiryDetail.orElseThrow(InquiryNotFoundException::new));
    }

    @GetMapping("notices")
    public ResponseEntity<NoticesCriteriaDTO> searchNotices(@RequestParam int page) {
        NoticesCriteriaDTO noticesCriteriaDTO = noticeService.getList(page);
        return ResponseEntity.ok().body(noticesCriteriaDTO);
    }

    @GetMapping("notices/{id}")
    public ResponseEntity<NoticeDTO> noticeDetail(@PathVariable Long id) {
        NoticeDTO notice = noticeService.getNotice(id).orElseThrow(NoticeNotFoundException::new);
        return ResponseEntity.ok().body(notice);
    }

    @PostMapping("members")
    public ResponseEntity<MemberCriteriaDTO> searchMembers(@RequestBody Search search) {
        MemberCriteriaDTO members = memberService.getListAllStatus(search);
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("members/{memberId}")
    public ResponseEntity<MemberDTO> memberDetail(@PathVariable Long memberId) {
        MemberDTO member = memberService.getMemberByIdAllStatus(memberId).orElseThrow(MemberNotFoundException::new);
        log.info("출력을 잘됨");
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("doctors")
    public ResponseEntity<DoctorCriteriaDTO> searchDoctors(@RequestBody Search search) {
        DoctorCriteriaDTO doctors = doctorService.getListAllStatus(search, Status.ACTIVE.getValue());
        return ResponseEntity.ok().body(doctors);
    }


    @GetMapping({"doctors/{doctorId}","doctors/pending/{doctorId}"})
    public ResponseEntity<DoctorHospitalDTO> doctorDetail(@PathVariable Long doctorId) {
        DoctorHospitalDTO doctor = doctorService.getDoctorAdminById(doctorId).orElseThrow(DoctorNotFoundException::new);
        return ResponseEntity.ok().body(doctor);
    }

    @PostMapping("doctors/pending")
    public ResponseEntity<DoctorCriteriaDTO> searchPendingDoctors(@RequestBody Search search) {
        log.info("{}",search.toString());
        DoctorCriteriaDTO doctors = doctorService.getListAllStatus(search, Status.INACTIVE.getValue());
        return ResponseEntity.ok().body(doctors);
    }

    @PutMapping("doctors/{doctorId}/approve")
    public ResponseEntity<String> approveDoctor(@PathVariable Long doctorId) {
        boolean isDoctorExist = doctorService.approve(doctorId);
        if (isDoctorExist) {
            return ResponseEntity.ok().body("승인되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("의사가 존재하지 않습니다.");
    }

    @PutMapping("doctors/{doctorId}/reject")
    public ResponseEntity<String> rejectDoctor(@PathVariable Long doctorId) {
        boolean isDoctorExist = memberService.reject(doctorId);
        if (isDoctorExist) {
            return ResponseEntity.ok().body("거절되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("의사가 존재하지 않습니다.");
    }


    @PostMapping("payment")
    public ResponseEntity<PaymentCriteriaDTO> searchPayments(@RequestBody Search search) {
        PaymentCriteriaDTO payments = paymentService.getPayments(search);
        return ResponseEntity.ok().body(payments);

    }
}
