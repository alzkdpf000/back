package com.example.back.mapper.doctor;

import com.example.back.common.enumeration.Role;
import com.example.back.domain.member.MemberVO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.mapper.member.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class DoctorMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private DoctorMapper doctorMapper;


    @Test
    @Transactional
    public void testInsertDoctor(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberRole(Role.DOCTOR);
        memberDTO.setMemberEmail("tes@gmail.com");
        memberDTO.setMemberPassword("123456");
        memberDTO.setMemberName("test");
        memberDTO.setMemberPhone("010-1234-1243");
        memberMapper.insertMember(memberDTO);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setMemberId(memberDTO.getId());
        doctorDTO.setDoctorLicenseNumber("!23434");
        doctorDTO.setDoctorSpecialty("내과");
        doctorDTO.setHospitalId(1L);

//        doctorMapper.insertDoctor(doctorDTO);
//        log.info("Doctor Inserted: {}", doctorDTO);


    }

}
