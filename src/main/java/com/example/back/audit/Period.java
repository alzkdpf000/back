package com.example.back.audit;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
@Getter
public class Period {
    private String createdDate;
    private String updatedDate;
}
