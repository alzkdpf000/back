package com.example.back.service.payment;


import com.example.back.domain.payment.PaymentVO;
import com.example.back.domain.vitahistory.VitaHistoryVO;
import com.example.back.dto.payment.PaymentCriteriaDTO;
import com.example.back.dto.payment.PaymentDTO;
import com.example.back.dto.vitahistory.VitaHistoryDTO;
import com.example.back.util.Search;

public interface PaymentService {

    //    관리자 페이지 충전 사용 내역들
    public PaymentCriteriaDTO getPayments(Search search);




    default PaymentVO toVO(PaymentDTO paymentDTO){
        return PaymentVO.builder()
                .id(paymentDTO.getId())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .memberId(paymentDTO.getMemberId())
                .paymentAmount(paymentDTO.getPaymentAmount())
                .paymentStatus(paymentDTO.getPaymentStatus())
                .createdDatetime(paymentDTO.getCreatedDatetime())
                .updatedDatetime(paymentDTO.getUpdatedDatetime())
                .paymentTransactionId(paymentDTO.getPaymentTransactionId())
                .build();
    }
}
