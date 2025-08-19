package com.example.back.dto.payment;


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
public class PaymentDTO {
    private Long id;
    private Long memberId;
    private String paymentMethod;
    private String paymentAmount;
    private String paymentStatus;
    private String paymentTransactionId;
    private String createdDatetime;
    private String updatedDatetime;
}
