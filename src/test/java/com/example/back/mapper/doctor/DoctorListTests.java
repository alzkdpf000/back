package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.service.doctor.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DoctorListTests {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorMapper doctorMapper;


//    @Test
//    public void testInsertDoctorList(){
//        DoctorListDTO doctor = DoctorListDTO.builder()
//                .memberId(1L)
//                .memberName("홍길동")
//                .doctorLicenseNumber("LIC-2025-001")
//                .doctorSpecialty("내과")
//                .hospitalId(3L)
//                .doctorStatus(Status.ACTIVE)
//                .build();
//
//            doctorListMapper.insertDoctor(doctor);
//    }

//    @Test
//    public void testSelectDoctorList(){
//        DoctorListDTO doctorListDTO = new DoctorListDTO();
//        log.info("doctorListDTO = {}", doctorListDTO);
//        doctorMapper.selectAll(doctorListDTO).stream().map(DoctorListDTO::toString).forEach(log::info);




    }
}