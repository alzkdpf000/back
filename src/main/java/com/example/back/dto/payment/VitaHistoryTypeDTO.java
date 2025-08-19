package com.example.back.dto.payment;

import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class VitaHistoryTypeDTO {
    private Result result;
    private Type type;
}
