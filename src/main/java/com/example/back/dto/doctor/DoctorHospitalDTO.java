package com.example.back.dto.doctor;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Status;
import com.example.back.dto.counselreply.CounselReplyDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private String memberPhone;
    private String memberEmail;
    private String kakaoEmail;
    private Status memberStatus;
    private Status doctorStatus;
    private Long memberId;
    private Provider memberProvider;
    private String memberVitaAmount;
    private Long hospitalId;
    private String createdDatetime;
    private String updatedDatetime;
    private String hospitalName;
    private String hospitalPhone;
    private String roadAddress;
    private String detailAddress;
    private String zipCode;
    private String hospitalHomepageUrl;
    private List<CounselReplyDTO> replies;

}
