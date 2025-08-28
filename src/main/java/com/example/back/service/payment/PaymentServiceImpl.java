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
import java.util.Map;

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

    // 결제금액 → 보너스 매핑
    private static final Map<Integer, Integer> PRICE_TO_VITA = Map.of(
            5000, 200,
            3000, 100
    );

    @Override
    @Transactional
    public void processPayment(PaymentDTO paymentDTO) {
        paymentDAO.insertPayment(paymentDTO);

        int vitaChange = 0;
        Integer paymentAmount = paymentDTO.getPaymentAmount();

        if (paymentAmount != null) {
            switch (paymentDTO.getPaymentStatus()) {
                case "success":
                    vitaChange = PRICE_TO_VITA.getOrDefault(paymentAmount, 0);
                    break;
                case "cancel":
                    vitaChange = -PRICE_TO_VITA.getOrDefault(Math.abs(paymentAmount), 0);
                    break;
                default:
                    vitaChange = 0;
                    break;
            }
        }

        paymentDAO.updatePaymentStatus(paymentDTO.getPaymentTransactionId(), paymentDTO.getPaymentStatus());
        paymentDAO.updateMemberVita(paymentDTO.getMemberId(), vitaChange);
    }

    @Override
    public List<PaymentDTO> getPaymentList(Long memberId) {
        return paymentDAO.paymentList(memberId);
    }
}
