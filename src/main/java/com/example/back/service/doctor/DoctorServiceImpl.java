package com.example.back.service.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.domain.doctor.DoctorVO;
import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.domain.hospital.HospitalVO;
import com.example.back.domain.member.MemberVO;
import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.dto.doctor.*;
import com.example.back.dto.member.MemberDTO;
import com.example.back.repository.counselreply.CounselReplyDAO;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.service.hospital.HospitalService;
import com.example.back.service.likes.LikesService;
import com.example.back.service.member.MemberService;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO;
    private final CounselReplyDAO counselReplyDAO;
    private final MemberService memberService;
    private final HospitalService hospitalService;
    private final LikesService likesService;
    private final com.example.back.dao.likes.LikesDAO likesDAO;

    @Override
    public DoctorListCriteriaDTO getList(int page, Long currentMemberId, Search search) {
        Criteria criteria = new Criteria(page, doctorDAO.findCountDoctorList());
        criteria.setCurrentMemberId(currentMemberId);

        List<DoctorListDTO> doctorsList = doctorDAO.findDoctorList(criteria, );
        doctorsList.forEach(doctor -> {
            doctor.setLikesCount(likesDAO.getLikesCount(doctor.getId()));
        });

        // 1개 더 가져왔으면 마지막 제거
        boolean hasMore = doctorsList.size() > criteria.getRowCount();
        if (hasMore) {
            doctorsList.remove(doctorsList.size() - 1);
        }
        criteria.setHasMore(hasMore);

        DoctorListCriteriaDTO doctorListCriteriaDTO = new DoctorListCriteriaDTO();
        doctorListCriteriaDTO.setDoctorsList(doctorsList);
        doctorListCriteriaDTO.setCriteria(criteria);

        return doctorListCriteriaDTO;
    }

    @Override
    public DoctorCriteriaDTO getListAllStatus(Search search, String doctorStatus) {
        DoctorCriteriaDTO doctorCriteriaDTO = new DoctorCriteriaDTO();
        int total = doctorDAO.findCountAllStatus(search, doctorStatus);
        Criteria criteria = new Criteria(search.getPage(), total);
        List<DoctorDTO> doctorsList = doctorDAO.findAllStatus(criteria, doctorStatus, search);
        doctorsList.forEach(doctor -> {
            doctor.setCreatedDate(DateUtils.getCreatedDate(doctor.getCreatedDatetime()));
        });
        criteria.setHasMore(doctorsList.size() > criteria.getRowCount());
        if (criteria.isHasMore()) {
            doctorsList.remove(doctorsList.size() - 1);
        }
        doctorCriteriaDTO.setDoctorsList(doctorsList);
        doctorCriteriaDTO.setCriteria(criteria);
        doctorCriteriaDTO.setTotal(total);
        doctorCriteriaDTO.setSearch(search);
        return doctorCriteriaDTO;
    }

    @Override
    public Optional<DoctorHospitalDTO> getDoctorAdminById(Long doctorId) {
        Optional<DoctorHospitalDTO> doctor = doctorDAO.findDoctorById(doctorId);
        doctor.ifPresent(data -> {
            List<CounselReplyDTO> replies = counselReplyDAO.findTop3ConsultationPostsWithReplies(doctorId);
            replies.forEach(reply -> {
                reply.setCreatedDate(DateUtils.getCreatedDate(reply.getCreatedDatetime()));
            });
            data.setReplies(replies);
        });
        return doctor;
    }

    @Override
    public boolean approve(Long doctorId) {
        return doctorDAO.approveDoctor(doctorId) > 0;
    }

    @Override
    public void join(DoctorDTO doctorDTO, MemberDTO memberDTO, HospitalDTO hospitalDTO) {
        // 1. 병원 상태 활성화
        hospitalDTO.setHospitalStatus(Status.ACTIVE.name());

        // 2. 일반회원 정보 처리
        if (memberDTO != null && memberDTO.getId() != null) {
            // 기존 회원이면 ID만 연결
            doctorDTO.setMemberId(memberDTO.getId());
        } else {
            // 신규 회원이면 DTO 그대로 넘김
            memberDTO = memberService.join(memberDTO);
            doctorDTO.setMemberId(memberDTO.getId());
        }

        log.info("Member ID after join: {}", doctorDTO.getMemberId());

        // 3. 병원 정보 추가
        Long hospitalId = hospitalService.register(hospitalDTO);
        doctorDTO.setHospitalId(hospitalId);

        log.info("Hospital ID after register: {}", doctorDTO.getHospitalId());

        // 4. 의사 정보 추가
        doctorDTO.setMemberStatus(Status.ACTIVE);
        doctorDTO.setDoctorStatus(Status.ACTIVE);
        doctorDAO.insertDoctor(doctorDTO);

        log.info("Doctor 가입 완료 => memberId={}, hospitalId={}, license={}, specialty={}",
                doctorDTO.getMemberId(),
                doctorDTO.getHospitalId(),
                doctorDTO.getDoctorLicenseNumber(),
                doctorDTO.getDoctorSpecialty());
    }



}

