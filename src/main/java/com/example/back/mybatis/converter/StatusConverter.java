package com.example.back.mybatis.converter;


import com.example.back.common.enumeration.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        return Status.getStatusFromValue(source);
    }
}
