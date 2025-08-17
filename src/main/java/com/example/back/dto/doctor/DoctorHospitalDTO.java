package com.example.back.dto.doctor;

import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class DoctorHospitalDTO {
    private Long id;
    private String memberName;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private Long memberId;
    private Long hospitalId;
    private String createdDatetime;
    private String updatedDatetime;
    private String hospitalName;
    private String hospitalPhone;
    private String roadAddress;
    private String detailAddress;

}
