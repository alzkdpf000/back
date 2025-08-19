package com.example.back.domain.hospital;

import com.example.back.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HospitalDTO {
    private Long id;
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalHomePageUrl;
    private String hospitalStatus;
    private Long hospitalAddressId;
    private String roadAddress;
    private String detailAddress;
    private String zipCode;
    private String createdDate;
    private String updatedDate;
}
