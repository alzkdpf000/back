package com.example.back.config.mybatis.converter;


import com.example.back.common.enumeration.Status;
import com.example.back.common.enumeration.Type;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeConverter implements Converter<String, Type> {

    @Override
    public Type convert(String source) {
        return Type.getTypeFromValue(source);
    }
}
