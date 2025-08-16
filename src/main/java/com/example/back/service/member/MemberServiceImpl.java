package com.example.back.service.member;

import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.dto.member.MemberCriteriaDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.repository.member.MemberDAO;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService{

    private final MemberDAO memberDAO;
    private final ConsultationPostDAO  consultationPostDAO;
    @Override
    public void join(MemberDTO memberDTO) {
        memberDAO.save(toVO(memberDTO));
    }

    @Override
    public boolean isExistMemberEmail(String memberEmail) {
        return memberDAO.isExistMemberEmail(memberEmail);
    }

    @Override
    public MemberCriteriaDTO getList(int page) {
        MemberCriteriaDTO memberCriteriaDTO = new MemberCriteriaDTO();
        int total = memberDAO.findCountAll();
        Criteria criteria = new Criteria(page,total);
        List<MemberDTO> members = memberDAO.findAll(criteria);
        members.forEach(member -> {
            member.setCreatedDate(DateUtils.getCreatedDate(member.getCreatedDatetime()));
        });
        memberCriteriaDTO.setMembers(members);
        memberCriteriaDTO.setTotal(total);
        memberCriteriaDTO.setCriteria(criteria);
        return memberCriteriaDTO;
    }

    @Override
    public Optional<MemberDTO> getMemberByIdAllStatus(Long memberId) {
        Optional<MemberDTO> member = memberDAO.findMemberByIdAllStatus(memberId);
        member.ifPresent((memberDTO)->{
            List<ConsultationPostDTO> posts = consultationPostDAO.findTop3ByMemberId(memberId);
            posts.forEach((post) -> {
                post.setCreatedDate(DateUtils.getCreatedDate(post.getCreatedDatetime()));
            });
            memberDTO.setConsultationPosts(posts);
        });

        return member;
    }

}
