package com.example.back.mapper.doctor;

import com.example.back.domain.member.MemberVO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.mapper.member.MemberMapper;
import lombok.extern.slf4j.Slf4j;
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
    @Autowired
    private MemberDTO memberDTO;

    @Test
    public void testInsertDoctor(){
        MemberVO member = MemberVO.builder()
                .memberEmail("tes@gmail.com")
                .memberName("test1")
                .memberPhone("010-0000-0000")
                .memberPassword("1234")
                .build();
        memberMapper.insertMember(memberDTO);
        log.info("Member Inserted: {}", member);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setMemberId(member.getId());
        doctorDTO.setDoctorLicenseNumber("!23434");
        doctorDTO.setDoctorSpecialty("내과");
        doctorDTO.setHospitalId(1L);

//        doctorMapper.insertDoctor(doctorDTO);
//        log.info("Doctor Inserted: {}", doctorDTO);


    }

}
