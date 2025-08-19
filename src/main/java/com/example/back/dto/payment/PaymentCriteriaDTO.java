package com.example.back.dto.payment;

import com.example.back.dto.vitahistory.VitaHistoryDTO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class PaymentCriteriaDTO {
    private List<PaymentMemberVitaDTO> payments;
    private Criteria criteria;
    private Search search;
    private int total;
}
