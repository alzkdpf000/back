package com.example.back.mapper.member;

import com.example.back.common.enumeration.Role;
import com.example.back.domain.member.MemberVO;
import com.example.back.domain.membervisited.MemberVisitedVO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.dto.membervisited.MemberVisitedDTO;
import com.example.back.mapper.membervisited.MemberVisitedMapper;
import com.example.back.repository.member.MemberDAO;
import com.example.back.repository.memberfile.MemberFileDAO;
import com.example.back.repository.membervisited.MemberVisitedDAO;
import com.example.back.service.member.MemberService;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberVisitedMapper memberVisitedMapper;
    @Autowired
    private MemberDTO memberDTO;
    @Autowired
    private MemberFileDAO memberFileDAO;

    @Test
    public void testInsertMember(){
        MemberDTO member = new MemberDTO();
        member.setMemberPhone("010-0000-0000");
        member.setMemberName("test");
        member.setMemberEmail("testw3828@naver.com");
        member.setMemberPassword("test123456");
        member.setMemberRole(Role.MEMBER);

        memberMapper.insertMember(member);

    }


    @Test
    public void testExistMemberEmail(){
        boolean isExist = memberMapper.existMemberEmail("test@gmail.com");
        log.info("isExist: {}", isExist);
    }

    @Test
    public void testSelectCountAllStatus(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        log.info("{}",memberMapper.selectCountAllStatus(search));
    }
    @Test
    public void testSelectMembers(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        Criteria criteria = new Criteria(1,memberMapper.selectCountAllStatus(search));
        log.info("{}",memberMapper.selectMembers(criteria,search));
    }

    @Test
    public void testFindCountAll(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        log.info("{}",memberDAO.findCountAllStatus(search));
    }

    @Test
    public void testFindAll(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        Criteria criteria = new Criteria(1,memberDAO.findCountAllStatus(search));
        log.info("{}",memberDAO.findAll(criteria,search));
    }

    @Test
    public void testGetList(){
        Search search = new Search();
        search.setPage(1);
        search.setKeyword("test");
        log.info(memberService.getListAllStatus(search).toString());
    }
    @Test
    public void testSelectMemberByIdAllStatus(){
        log.info("{}",memberMapper.selectMemberByIdAllStatus(1L));
    }
    @Test
    public void testFindMemberByIdAllStatus(){
        log.info("{}",memberDAO.findMemberByIdAllStatus(1L));
    }
    @Test
    public void testFindById(){
        log.info("{}",memberService.getMemberByIdAllStatus(143L));
        Assertions.assertThat(memberDAO.findMemberByIdAllStatus(143L)).isNotNull();
    }
    @Test
    @Transactional
    public void testUpdateDoctorStatusToRejected(){
        memberMapper.updateDoctorStatusToRejected(10L);
        log.info("{}",memberMapper.selectMemberByIdAllStatus(10L));
    }

    @Test
    @Transactional
    public void testRejectDoctor(){
        memberDAO.rejectDoctor(11L);
        log.info("{}",memberDAO.findMemberByIdAllStatus(11L));
    }

    @Test
    @Transactional
    public void testReject(){
        log.info("{}",memberService.reject(11L));
    }

    @Test
    public void testSelectMemberForLogin() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("qwer111@naver.com");
        memberDTO.setMemberPassword("qwer111");
        memberDTO.setMemberRole(Role.DOCTOR);
        log.info(memberDTO.toString());
        Optional<MemberDTO> foundMember = memberMapper.selectMemberForLogin(memberDTO, "doctor");
        log.info("foundMember:{}",foundMember.isPresent());
//        foundMember.ifPresent(member -> {
//            log.info("member:{}", member);
//        });
    }
    @Test
    public void testSelectMonthlyJoin(){
        log.info(memberMapper.selectMonthlyJoin().toString());
    }
    @Test
    public void testSelectTodayJoin(){
        log.info("{}",memberMapper.selectCountTodayJoin());
    }
    @Test
    public void testFindMonthlyJoin(){
        log.info(memberDAO.findMonthlyJoin().toString());
    }
    @Test
    public void testFindTodayJoin(){
        log.info("{}",memberDAO.findCountTodayJoin());
    }

    @Test
    public void testGetStatics(){
        log.info("{}",memberService.getStatics());
    }

    @Test
    public void testInsertMemberVisited(){
        MemberVisitedVO memberVisitedVO = new MemberVisitedVO();
        memberVisitedVO.setMemberId(1L);

        memberVisitedMapper.insertMemberVisited(memberVisitedVO);
    }

    @Test
    public void testSelectMemberVisited(){
        log.info("{}", memberVisitedMapper.selectMemberVisited());
    }


    @Test
    public void testSelectMemberForKakao(){
        log.info("{}", memberMapper.selectMemberForKakaoEmail("kakaoEmail"));
    }


    @Test
    public void testGetMemberByIdAllStatus(){
        log.info("{}", memberService.getMemberByIdAllStatus(143L));

    }




}
