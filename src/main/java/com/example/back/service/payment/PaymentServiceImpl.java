package com.example.back.service.payment;

import com.example.back.common.exception.PaymentUpdateFailException;
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
    @Transactional(rollbackFor = Exception.class)
    public void processPayment(PaymentDTO paymentDTO) {
        paymentDAO.insertPayment(paymentDTO);

        int vitaChange;
        Integer paymentAmount = paymentDTO.getPaymentAmount();

        if (paymentAmount == null) {
            throw new PaymentUpdateFailException("결제 금액이 null입니다.");
        }

        switch (paymentDTO.getPaymentStatus()) {
            case "success":
                vitaChange = PRICE_TO_VITA.getOrDefault(paymentAmount, 0);
                break;
            case "cancel":
                vitaChange = -PRICE_TO_VITA.getOrDefault(Math.abs(paymentAmount), 0);
                break;
            default:
                throw new PaymentUpdateFailException(
                        "알 수 없는 결제 상태: " + paymentDTO.getPaymentStatus()
                );
        }

        // 결제 상태 업데이트
        int updatedRows = paymentDAO.updatePaymentStatus(
                paymentDTO.getPaymentTransactionId(),
                paymentDTO.getPaymentStatus()
        );
        if (updatedRows == 0) {
            throw new PaymentUpdateFailException("결제 상태 업데이트 실패");
        }

        // 회원 비타 업데이트
        int updatedVitaRows = paymentDAO.updateMemberVita(
                paymentDTO.getMemberId(),
                vitaChange
        );
        if (updatedVitaRows == 0) {
            throw new PaymentUpdateFailException("회원 비타 잔액 업데이트 실패");
        }
    }

    @Override
    public List<PaymentDTO> getPaymentList(Long memberId) {
        return paymentDAO.paymentList(memberId);
    }
}
