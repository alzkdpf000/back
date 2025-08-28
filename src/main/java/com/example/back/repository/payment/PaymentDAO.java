package com.example.back.repository.payment;

import com.example.back.dto.payment.PaymentAmountDTO;
import com.example.back.dto.payment.PaymentCriteriaDTO;
import com.example.back.dto.payment.PaymentDTO;
import com.example.back.dto.payment.PaymentMemberVitaDTO;
import com.example.back.dto.vitahistory.VitaHistoryDTO;
import com.example.back.mapper.payment.PaymentMapper;
import com.example.back.mapper.vitahistory.VitaHistoryMapper;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    //    관리자 페이지 결제 사용 내역들
    public List<PaymentMemberVitaDTO> findPayments(Criteria criteria, Search search){
        return paymentMapper.searchPayments(criteria,search);
    }

    //    관리자 페이지 결제 수
    public int findCountPayment(Search search){
        return paymentMapper.searchCountPayment(search);
    }
    public List<PaymentAmountDTO> findPaymentAmount(Search search){
        return paymentMapper.searchPaymentAmount(search);
    }

    public int insertPayment(PaymentDTO payment) {
        return paymentMapper.insertPayment(payment);
    }

    public int updatePaymentStatus(String transactionId, String status) {
        return paymentMapper.updatePaymentStatus(transactionId, status);
    }

    public int updateMemberVita(Long memberId, int amount) {
        return paymentMapper.updateMemberVita(memberId, amount);
    }

    // 결제 내역 전체 조회
    public List<PaymentDTO> paymentList(Long memberId) {
        return paymentMapper.paymentList(memberId);
    }
}
