package com.example.back.config.mybatis.converter;


import com.example.back.common.enumeration.PaymentStatus;
import com.example.back.common.enumeration.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentStatusConverter implements Converter<String, PaymentStatus> {

    @Override
    public PaymentStatus convert(String source) {
        return PaymentStatus.getStatusFromValue(source);
    }
}
