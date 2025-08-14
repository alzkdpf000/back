package com.example.back.domain.hospital;

import com.example.back.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(of = "id")
public class HospitalVO extends Period {
    private Long id;
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalHomePageUrl;
    private String hospitalStatus;
    private Long hospitalId;
    private String hospitalRoadAddress;
    private String hospitalDetailAddress;
}
