package com.example.back.mapper.payment;

import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Type;
import com.example.back.dto.vitahistory.VitaHistoryDTO;
import com.example.back.mapper.vitahistory.VitaHistoryMapper;
import com.example.back.repository.payment.PaymentDAO;
import com.example.back.repository.vitahistory.VitaHistoryDAO;
import com.example.back.service.payment.PaymentService;
import com.example.back.service.vitahistory.VitaHistoryService;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class PaymentTests {
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private PaymentDAO paymentDAO;
    @Autowired
    private PaymentService paymentService;


    @Test
    public void testSearchPayments(){
        Criteria criteria = new Criteria(1,10);
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);
        log.info(paymentMapper.searchPayments(criteria,search).toString());
    }
    @Test
    public void testFindPayments(){
        Criteria criteria = new Criteria(1,10);
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);
        log.info(paymentDAO.findPayments(criteria,search).toString());
    }

    @Test
    public void testSearchCountPayment(){
//        Criteria criteria = new Criteria(1,10);
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);
        log.info("{}",paymentMapper.searchCountPayment(search));
    }

    @Test
    public void testfindCountPayment(){
//        Criteria criteria = new Criteria(1,10);
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);
        log.info("{}",paymentDAO.findCountPayment(search));
    }

    @Test
    public void testfindPayments(){
        Search search = new Search();
        search.setKeyword("");
        search.setPage(1);

        log.info("{}",paymentService.getPayments(search));
    }
}
