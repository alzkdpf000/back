package com.example.back.dto.payment;

import com.example.back.common.enumeration.PaymentStatus;
import com.example.back.common.enumeration.Provider;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class PaymentMemberVitaDTO {
    private Long id;
    private String memberEmail;
    private String memberName;
    private Provider memberProvider;
    private String memberKakaoEmail;
    private String createdDatetime;
    private int paymentAmount;
    private String paymentMethod;
    private String paymentTransactionId;
    private PaymentStatus paymentStatus;
    private String vitaHistoryProductName;
}
