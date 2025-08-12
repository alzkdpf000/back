package com.example.back.domain.doctor;


import com.example.back.domain.member.MemberVO;

public class DoctorVO extends MemberVO {
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private HospitalVO hospital;
}
