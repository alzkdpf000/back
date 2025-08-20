package com.example.back.domain.payment;

import com.example.back.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(of="id")
public class PaymentVO extends Period {
//    id               bigint  unsigned auto_increment primary key,
//    member_id        bigint         not null, -- 결제한 회원
//    payment_method   varchar(50)    not null, -- 결제 수단 (우리는 카카오페이만 존재)
//    payment_amount   decimal(10, 2) not null, -- 결제 금액 (실제 원화/달러)
//    payment_status   enum('pending','success','cancel')   default 'pending', -- pending, success, cancel
//    payment_transaction_id   varchar(100) unique,     -- pg사에서 내려오는 결제 번호
//    created_datetime datetime default current_timestamp,
//    updated_datetime datetime default current_timestamp
    private Long id;
    private Long memberId;
    private String paymentMethod;
    private String paymentAmount;
    private String paymentStatus;
    private String paymentTransactionId;
}
