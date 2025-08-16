package com.example.back.mapper.member;

import com.example.back.domain.member.MemberVO;
import com.example.back.repository.member.MemberDAO;
import com.example.back.service.member.MemberService;
import com.example.back.util.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberService memberService;

    @Test
    public void testInsertMember(){
        MemberVO member = MemberVO.builder()
                .memberEmail("test@gmail.com")
                .memberName("test")
                .memberPhone("010-0000-0000")
                .memberPassword("1234")
                .build();

        memberMapper.insertMember(member);

    }

    @Test
    public void testExistMemberEmail(){
        boolean isExist = memberMapper.existMemberEmail("test@gmail.com");
        log.info("isExist: {}", isExist);
    }

    @Test
    public void testSelectCountAll(){
        log.info("{}",memberMapper.selectCountAll());
    }
    @Test
    public void testSelectMembers(){
        Criteria criteria = new Criteria(1,memberMapper.selectCountAll());
        log.info("{}",memberMapper.selectMembers(criteria));
    }

    @Test
    public void testFindCountAll(){
        log.info("{}",memberDAO.findCountAll());
    }

    @Test
    public void testFindAll(){
        Criteria criteria = new Criteria(1,memberDAO.findCountAll());
        log.info("{}",memberDAO.findAll(criteria));
    }

    @Test
    public void testGetList(){
        log.info(memberService.getList(1).toString());
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
        log.info("{}",memberService.getMemberByIdAllStatus(1L));
        Assertions.assertThat(memberDAO.findMemberByIdAllStatus(1L)).isNotNull();
    }

}
