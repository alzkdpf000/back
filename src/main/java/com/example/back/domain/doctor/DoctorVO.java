package com.example.back.domain.doctor;


import ch.qos.logback.core.status.Status;
import com.example.back.audit.Period;
import com.example.back.domain.hospital.HospitalVO;
import com.example.back.domain.member.MemberVO;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter @ToString(callSuper = true)
@Setter
@SuperBuilder
@EqualsAndHashCode(of="id")
public class DoctorVO extends Period {
    private Long id;
    private MemberVO member;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private HospitalVO hospital;
}
