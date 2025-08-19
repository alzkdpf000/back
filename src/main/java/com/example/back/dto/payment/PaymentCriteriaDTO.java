package com.example.back.dto.payment;

import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PaymentCriteriaDTO {
    private List<PaymentMemberVitaDTO> payments;
    private Criteria criteria;
    private Search search;
    private List<PaymentAmountDTO> amounts;
    private int total;
}
