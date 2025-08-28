package com.example.back.mapper.payment;

import com.example.back.dto.payment.PaymentAmountDTO;
import com.example.back.dto.payment.PaymentCriteriaDTO;
import com.example.back.dto.payment.PaymentDTO;
import com.example.back.dto.payment.PaymentMemberVitaDTO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    //    관리자 페이지 결제 사용 내역들
    public List<PaymentMemberVitaDTO> searchPayments(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //    관리자 페이지 결제 수
    public int searchCountPayment(@Param("search") Search search);

    // 관리자 페이지 결제액 정보
    public List<PaymentAmountDTO> searchPaymentAmount(@Param("search") Search search);

    // 결제 처리(결제정보입력, Payment상태 업데이트, 회원 Vita 업데이트)
    int insertPayment(PaymentDTO payment);
    int updatePaymentStatus(@Param("transactionId") String transactionId, @Param("status") String status);
    int updateMemberVita(@Param("memberId") Long memberId, @Param("amount") int amount);

    // 조회용(총 개수 조회, 목록 조회)
    int findCountPayment(@Param("memberId") Long memberId);
    List<PaymentDTO> paymentList(@Param("memberId") Long memberId);
}
