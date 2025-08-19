package com.example.back.config.mybatis.converter;


import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResultConverter implements Converter<String, Result> {

    @Override
    public Result convert(String source) {
        return Result.getResultFromValue(source);
    }
}
