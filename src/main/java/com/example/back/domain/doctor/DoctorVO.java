package com.example.back.domain.doctor;



import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import com.example.back.domain.hospital.HospitalVO;
import com.example.back.domain.member.MemberVO;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter @ToString(callSuper = true)
@SuperBuilder
@EqualsAndHashCode(of="id")
public class DoctorVO extends Period {
    private Long id;
    private Long memberId;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private Long hospitalId;
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalAddress;

}

