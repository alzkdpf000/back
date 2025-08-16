package com.example.back.mapper.doctor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DoctorListTests {

    @Autowired
    private DoctorListService doctorListService;

    @Autowired
    private DoctorListMapper doctorListMapper;


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
//
//    @Test
//    public void testSelectDoctorList(){
//        DoctorListDTO doctorListDTO = new DoctorListDTO();
//        log.info("doctorListDTO = {}", doctorListDTO);
//        doctorListMapper.selectDoctorList(doctorListDTO).stream().map(DoctorListDTO::toString).forEach(log::info);




//    }
}