package com.example.back.dto.doctor;

import com.example.back.common.enumeration.Provider;
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
public class DoctorDTO {
    private Long id;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private Long hospitalId;
    private String memberEmail;
    private String memberName;
    private String memberPhone;
    private Status memberStatus;
    private Provider provider;
    private String memberKakaoEmail;
    private String createdDatetime;
    private String createdDate;
    private int memberVitaAmount;
    private Long memberId;
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalRoadAddress;
    private String hospitalDetailAddress;
    private String updatedDatetime;
    private String updatedDate;
    private String memberPassword;
    private String zipCode;
}
