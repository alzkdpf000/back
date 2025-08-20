package com.example.back.dto.payment;


import com.example.back.common.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentAmountDTO {
    private PaymentStatus paymentStatus;
    private int paymentAmount;
}
