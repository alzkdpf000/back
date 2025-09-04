package com.example.back.controller.payment;

import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.payment.PaymentDTO;
import com.example.back.dto.payment.PaymentMemberVitaDTO;
import com.example.back.service.member.MemberService;
import com.example.back.service.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment/**")
public class PaymentController {

    private final MemberService memberService;
    private final HttpSession session;
    private final PaymentService paymentService;

    @GetMapping("")
    public String goToPaymentPage(Model model){
        // 세션에서 로그인 회원 정보 가져오기
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if(member == null) return "redirect:/member/loginmain";

        MemberDTO loginMember = memberService.getMemberByIdAllStatus(member.getId())
                .orElseThrow(IllegalArgumentException::new);

        List<PaymentDTO> payments = paymentService.getPaymentList(member.getId());

        model.addAttribute("member", loginMember);
        model.addAttribute("payments", paymentService.getPaymentList(member.getId()));

        return "payment/payment";
    }
}