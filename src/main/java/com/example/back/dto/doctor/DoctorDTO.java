package com.example.back.dto.doctor;

import com.example.back.domain.member.MemberVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@EqualsAndHashCode(of = "id")
public class DoctorDTO {
//    member_id             bigint unsigned primary key,
//    doctor_license_number varchar(20)     not null unique,
//    doctor_specialty      varchar(255)    not null,
//    hospital_id           bigint unsigned not null,
//    doctor_status         enum ('active','inactive') default 'inactive',

    private Long id;
    private MemberVO member;
    private String doctorLicenseNumber;
    private String doctorName;
}
