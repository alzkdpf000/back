package com.example.back.mapper.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.service.counselreply.CounselReplyService;
import com.example.back.service.counselreply.CounselReplyServiceImpl;
import com.example.back.util.Criteria;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j

public class DoctorListTests {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private DoctorListMapper doctorListMapper;

    @Autowired
    private CounselReplyService counselReplyService;

    @Autowired
    private DoctorDAO doctorDAO;
    @Autowired
    private CounselReplyServiceImpl counselReplyServiceImpl;

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

    @Test
    public void testSelectDoctorList() {
        Criteria criteria = new Criteria(1, 10);

        Long currentMemberId = 53L;

        Search search = new Search();
        search.setKeyword("test");
        search.setCategories(new String[]{"내과", "외과"});

        List<DoctorListDTO> doctorList = doctorMapper.selectDoctorList(criteria, search, currentMemberId);
        log.info("{}", doctorList);
    }


}