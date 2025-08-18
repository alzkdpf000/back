package com.example.back.mapper.doctor;

import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.util.Criteria;
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

    @Autowired
    private DoctorDAO doctorDAO;

    @Test
    public void testSelectAllStatus(){
        Criteria criteria = new Criteria(1,10);
        log.info("{}",doctorMapper.selectDoctorsByStatus(criteria,"active"));
    }
    @Test
    public void testFindAllStatus(){
        Criteria criteria = new Criteria(1,10);
        log.info("{}", doctorDAO.findAllStatus(criteria,"inactive"));
    }

    @Test
    public void testSelectCountAllStatus(){
        log.info("{}",doctorMapper.selectDoctorCountByStatus("active"));
    }
    @Test
    public void testFindCountAllStatus(){
        log.info("{}", doctorDAO.findCountAllStatus("inactive"));
    }

    @Test
    public void testSelectDoctorById(){
        log.info("{}",doctorMapper.selectDoctorById(60L));
    }

    @Test
    public void testFindDoctorById(){
        log.info("{}", doctorDAO.findDoctorById(60L));
    }


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

    @Test
    public void testSelectDoctorList(){
        DoctorListDTO doctorListDTO = new DoctorListDTO();
        log.info("doctorListDTO = {}", doctorListDTO);
//        doctorMapper.selectAll(doctorListDTO).stream().map(DoctorListDTO::toString).forEach(log::info);




    }
}