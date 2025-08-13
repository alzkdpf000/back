package com.example.back.config.mybatis.converter;

import com.example.back.common.enumeration.Provider;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter implements Converter<String, Provider> {


    @Override
    public Provider convert(String source) {
        return Provider.getProviderFromValue(source);
    }
}
