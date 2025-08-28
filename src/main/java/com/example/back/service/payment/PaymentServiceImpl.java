package com.example.back.service.payment;

import com.example.back.dto.payment.PaymentCriteriaDTO;
import com.example.back.dto.payment.PaymentDTO;
import com.example.back.dto.payment.PaymentMemberVitaDTO;
import com.example.back.repository.payment.PaymentDAO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;


    @Override
    public PaymentCriteriaDTO getPayments(Search search) {
        PaymentCriteriaDTO paymentCriteriaDTO = new PaymentCriteriaDTO();
        int total = paymentDAO.findCountPayment(search);
        Criteria criteria = new Criteria(search.getPage(), total);
        List<PaymentMemberVitaDTO> payments = paymentDAO.findPayments(criteria, search);
        paymentCriteriaDTO.setCriteria(criteria);
        paymentCriteriaDTO.setTotal(total);
        paymentCriteriaDTO.setSearch(search);
        paymentCriteriaDTO.setPayments(payments);
        paymentCriteriaDTO.setAmounts(paymentDAO.findPaymentAmount(search));
        return paymentCriteriaDTO;
    }

    @Override
    @Transactional
    public void processPayment(PaymentDTO paymentDTO) {
        // 결제 정보 insert
        paymentDAO.insertPayment(paymentDTO);


        // 결제 상태에 따른 처리
        int amount = Integer.parseInt(paymentDTO.getPaymentAmount());
        switch (paymentDTO.getPaymentStatus()) {
            case "success":
                paymentDAO.updatePaymentStatus(paymentDTO.getPaymentTransactionId(), "success");
                paymentDAO.updateMemberVita(paymentDTO.getMemberId(), amount);
                break;

            case "cancel":
                paymentDAO.updatePaymentStatus(paymentDTO.getPaymentTransactionId(), paymentDTO.getPaymentStatus());
                paymentDAO.updateMemberVita(paymentDTO.getMemberId(), -amount);
                break;

            default:
                paymentDAO.updatePaymentStatus(paymentDTO.getPaymentTransactionId(), paymentDTO.getPaymentStatus());
                break;
        }
    }
    @Override
    public List<PaymentDTO> getPaymentList(Long memberId) {
        return paymentDAO.paymentList(memberId);
    }
}
