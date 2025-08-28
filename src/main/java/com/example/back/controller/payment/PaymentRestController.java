package com.example.back.controller.payment;

import com.example.back.dto.consultationpost.ConsultationPostCriteriaDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.payment.PaymentDTO;
import com.example.back.service.consultationpost.ConsultationPostService;
import com.example.back.service.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/payment")
public class PaymentRestController {
    private final PaymentService paymentService;
    private final HttpSession session;

    @PostMapping("/complete")
    public ResponseEntity<Void> completePayment(@RequestBody PaymentDTO paymentDTO) {

        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if(member == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        paymentDTO.setMemberId(member.getId());
        paymentDTO.setPaymentStatus("success");

        if(paymentDTO.getPaymentMethod() == null || paymentDTO.getPaymentMethod().isEmpty()) {
            paymentDTO.setPaymentMethod("kakaoPay");
        }
        if(paymentDTO.getPaymentAmount() == null) {
            paymentDTO.setPaymentAmount(0);
        }

        paymentService.processPayment(paymentDTO);

        return ResponseEntity.ok().build();
    }
}
