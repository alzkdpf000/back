package com.example.back.dto.doctor;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.member.MemberDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@EqualsAndHashCode(of = "id")
public class DoctorDTO {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberPhone;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Long hospitalId;
}


