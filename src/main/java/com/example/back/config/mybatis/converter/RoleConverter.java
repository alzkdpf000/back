package com.example.back.config.mybatis.converter;


import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String source) {
        return Role.getRoleValue(source);
    }
}
