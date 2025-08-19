package com.example.back.mapper.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.util.Criteria;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        Criteria criteria = new Criteria(1,10);
        log.info("{}",doctorMapper.selectDoctorsByStatus(criteria, Status.ACTIVE.getValue(),search));
    }
    @Test
    public void testFindAllStatus(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        Criteria criteria = new Criteria(1,10);
        log.info("{}", doctorDAO.findAllStatus(criteria,Status.INACTIVE.getValue(),search));
    }

    @Test
    public void testSelectCountAllStatus(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        log.info("{}",doctorMapper.selectDoctorCountByStatus(search,Status.INACTIVE.getValue()));
    }
    @Test
    public void testFindCountAllStatus(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        log.info("{}", doctorDAO.findCountAllStatus(search,Status.INACTIVE.getValue()));
    }

    @Test
    public void testSelectDoctorById(){
        log.info("{}",doctorMapper.selectDoctorById(60L));
    }

    @Test
    public void testFindDoctorById() {
        log.info("{}", doctorDAO.findDoctorById(60L));
    }

    @Test
    @Transactional
    public void testUpdateDoctorStatusToApproved(){
        doctorMapper.updateDoctorStatusToApproved(69L);
        log.info("{}",doctorMapper.selectDoctorById(69L));
    }

    @Test
    @Transactional
    public void testApproveDoctor(){
        doctorDAO.approveDoctor(69L);
        log.info("{}",doctorDAO.findDoctorById(69L));
    }

    @Test
    @Transactional
    public void testApprove(){
        log.info("{}",doctorService.approve(69L));
//        public void testFindDoctorById(){
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

<<<<<<< HEAD
    @Test
    public void testSelectDoctorList(){
        DoctorListDTO doctorListDTO = new DoctorListDTO();
        log.info("doctorListDTO = {}", doctorListDTO);
=======
//    @Test
//    public void testSelectDoctorList(){
//        DoctorListDTO doctorListDTO = new DoctorListDTO();
//        log.info("doctorListDTO = {}", doctorListDTO);
>>>>>>> doctor/list
//        doctorMapper.selectAll(doctorListDTO).stream().map(DoctorListDTO::toString).forEach(log::info);




    }
}