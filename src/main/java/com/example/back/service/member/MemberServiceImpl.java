package com.example.back.service.member;

import com.example.back.common.enumeration.Role;
import com.example.back.domain.membervisited.MemberVisitedVO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.dto.member.MemberAdminStatics;
import com.example.back.dto.member.MemberCriteriaDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.repository.member.MemberDAO;
import com.example.back.repository.memberfile.MemberFileDAO;
import com.example.back.repository.membervisited.MemberVisitedDAO;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final ConsultationPostDAO consultationPostDAO;
    private final MemberVisitedDAO memberVisitedDAO;
    private final MemberFileDAO memberFileDAO;

    @Override
    public MemberDTO join(MemberDTO memberDTO) {
        memberDAO.save(memberDTO);
        return memberDTO;

    }


    @Override
    public boolean isExistMemberEmail(String memberEmail) {
        return memberDAO.isExistMemberEmail(memberEmail);
    }

    @Override
    public MemberCriteriaDTO getListAllStatus(Search search) {
        MemberCriteriaDTO memberCriteriaDTO = new MemberCriteriaDTO();
        int total = memberDAO.findCountAllStatus(search);
        Criteria criteria = new Criteria(search.getPage(), total);
        List<MemberDTO> members = memberDAO.findAll(criteria, search);
        members.forEach(member -> {
            member.setCreatedDate(DateUtils.getCreatedDate(member.getCreatedDatetime()));
        });
        memberCriteriaDTO.setMembers(members);
        memberCriteriaDTO.setTotal(total);
        memberCriteriaDTO.setCriteria(criteria);
        memberCriteriaDTO.setSearch(search);
        return memberCriteriaDTO;
    }

    @Override
    public Optional<MemberDTO> getMemberByIdAllStatus(Long memberId) {
        Optional<MemberDTO> member = memberDAO.findMemberByIdAllStatus(memberId);
        member.ifPresent((memberDTO) -> {
            List<ConsultationPostDTO> posts = consultationPostDAO.findTop3ByMemberId(memberId);
            posts.forEach((post) -> {
                post.setCreatedDate(DateUtils.getCreatedDate(post.getCreatedDatetime()));
            });
            memberDTO.setConsultationPosts(posts);
        });

        return member;
    }

    @Override
    public boolean reject(Long memberId) {
        return memberDAO.rejectDoctor(memberId) > 0;
    }


    //    로그인
    @Override
    public Optional<MemberDTO> login(MemberDTO memberDTO, String memberRole) {
        Optional<MemberDTO> member = memberDAO.findMemberEmailAndPassword(memberDTO, memberRole);
        member.ifPresent((m) ->{
            Optional<MemberFileDTO> file = memberFileDAO.selectMemberFile(m.getId());
            file.ifPresent(
                    f -> {m.setMemberFileDTO(f); log.info("파일 확인해보자{}",file.get()); }
            );
        });
        log.info("멤버 확인해보자 {}" ,member);
        return member;
    }

    @Override
    public MemberAdminStatics getStatics() {
        MemberAdminStatics statics = new MemberAdminStatics();
        statics.setTodayVisited(memberVisitedDAO.findCountTodayVisits());
        statics.setMonthlyJoins(memberDAO.findMonthlyJoin());
        statics.setMonthlyVisited(memberVisitedDAO.findMonthlyVisits());
        statics.setMonthlyJoins(memberDAO.findMonthlyJoin());
        return statics;

    }

    @Override
    public int insertMemberVisited(MemberVisitedVO memberVisitedVO) {
        memberVisitedDAO.insertMemberVisited(memberVisitedVO);
        memberVisitedVO.setId(memberVisitedVO.getId());
        return memberVisitedDAO.insertMemberVisited(memberVisitedVO);
    }

    @Override
    public boolean selectMemberVisited(Long memberId) {
        memberVisitedDAO.selectMemberVisited(memberId);
        return true;
    }

    @Override
    public Optional<MemberDTO> getKakaoMember(String kakaoEmail) {
        return memberDAO.findMemberByKakaoEmail(kakaoEmail);
    }

    @Override
    public void joinKakaoMember(MemberDTO memberDTO) {
        memberDAO.saveKakaoMember(toVO(memberDTO));

    }

    @Override
    public void updatePassword(String memberEmail, String memberPassword) {
        memberDAO.updatePassword(memberEmail, memberPassword);

    }


}
