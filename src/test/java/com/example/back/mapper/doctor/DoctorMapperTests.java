package com.example.back.mapper.doctor;

import com.example.back.common.enumeration.Role;
import com.example.back.common.enumeration.Status;
import com.example.back.domain.member.MemberVO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.mapper.member.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DoctorMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private DoctorMapper doctorMapper;


    @Test
    public void testInsertDoctor() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("abc+" + System.currentTimeMillis() + "@naver.com");
        memberDTO.setMemberPassword("qqq111");
        memberDTO.setMemberName("테스트");
        memberDTO.setMemberPhone("010-0000-0000");
        memberDTO.setMemberRole(Role.DOCTOR);

        memberMapper.insertMember(memberDTO);
        log.info("회원 추가: {}", memberDTO);
        Assertions.assertNotNull(memberDTO.getId());

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setHospitalName("테스트병원");
        doctorDTO.setHospitalPhone("010-2224-3333");
        doctorDTO.setHospitalRoadAddress("서울시 강남구");
        doctorDTO.setHospitalDetailAddress("서울병원");
        doctorDTO.setZipCode("12345");
        doctorDTO.setMemberId(memberDTO.getId());
        doctorDTO.setDoctorLicenseNumber("wwq212");
        doctorDTO.setDoctorSpecialty("내과");
        doctorDTO.setDoctorStatus(Status.ACTIVE);

        doctorMapper.insertHospital(doctorDTO);
        doctorMapper.insertHospitalAddress(doctorDTO);
        Assertions.assertNotNull(doctorDTO.getHospitalId());
        doctorMapper.insertJoinDoctor(doctorDTO);

        log.info("의사회원가입완료: {}", doctorDTO);
    }

}
