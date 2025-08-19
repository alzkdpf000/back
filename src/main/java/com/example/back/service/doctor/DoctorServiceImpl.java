package com.example.back.service.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.domain.doctor.DoctorVO;
import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.dto.doctor.*;
import com.example.back.dto.member.MemberDTO;
import com.example.back.repository.counselreply.CounselReplyDAO;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.service.hospital.HospitalService;
import com.example.back.service.member.MemberService;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
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
    private final DoctorDTO doctorDTO;
    private final MemberService memberService;
    private final HospitalService hospitalService;
    private final MemberDTO memberDTO;

    @Override
    public DoctorListCriteriaDTO getList(int page) {
        DoctorListCriteriaDTO doctorListCriteriaDTO = new DoctorListCriteriaDTO();
        Criteria criteria = new Criteria(page, doctorDAO.findCountDoctorList());
        List<DoctorListDTO> doctorsList = doctorDAO.findDoctorList(criteria);

        //        11개 가져왔으면, 마지막 1개 삭제
        criteria.setHasMore(doctorsList.size() > criteria.getRowCount());

        if(criteria.isHasMore()){
            doctorsList.remove(doctorsList.size() - 1);
        }
        doctorListCriteriaDTO.setDoctorsList(doctorsList);
        doctorListCriteriaDTO.setCriteria(criteria);
        return doctorListCriteriaDTO;
    }

    @Override
    public DoctorCriteriaDTO getListAllStatus(int page,String doctorStatus) {
        DoctorCriteriaDTO doctorCriteriaDTO = new DoctorCriteriaDTO();
        int total = doctorDAO.findCountAllStatus(doctorStatus);
        Criteria criteria = new Criteria(page, total);
        List<DoctorDTO> doctorsList = doctorDAO.findAllStatus(criteria,doctorStatus);
        doctorsList.forEach(doctor ->{
            doctor.setCreatedDate(DateUtils.getCreatedDate(doctor.getCreatedDatetime()));
        });
        criteria.setHasMore(doctorsList.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            doctorsList.remove(doctorsList.size() - 1);
        }
        doctorCriteriaDTO.setDoctorsList(doctorsList);
        doctorCriteriaDTO.setCriteria(criteria);
        doctorCriteriaDTO.setTotal(total);
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
        hospitalDTO.setHospitalPhone(doctorDTO.getHospitalPhone());
        log.info(doctorDTO.toString());

//      일반회원 정보 추가
        memberService.join(memberDTO);
        doctorDTO.setMemberId(memberDTO.getId());


//      병원 정보 추가
        hospitalService.register(hospitalDTO);
        doctorDTO.setHospitalId(hospitalDTO.getId());


//      의사 정보 추가
        doctorDTO.setMemberStatus(Status.valueOf("ACTIVE"));
        doctorDAO.insertDoctor(doctorDTO);

    }


}