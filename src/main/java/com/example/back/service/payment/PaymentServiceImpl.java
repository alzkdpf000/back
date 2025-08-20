package com.example.back.service.payment;

import com.example.back.dto.payment.PaymentCriteriaDTO;
import com.example.back.dto.payment.PaymentMemberVitaDTO;
import com.example.back.repository.payment.PaymentDAO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
