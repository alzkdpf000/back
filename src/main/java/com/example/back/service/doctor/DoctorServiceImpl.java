package com.example.back.service.doctor;

import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.dto.doctor.*;
import com.example.back.repository.counselreply.CounselReplyDAO;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO;
    private final CounselReplyDAO counselReplyDAO;

    @Override
    public DoctorListCriteriaDTO getList(int page, Long currentMemberId) {
        Criteria criteria = new Criteria(page, doctorDAO.findCountDoctorList());
        criteria.setCurrentMemberId(currentMemberId);

        List<DoctorListDTO> doctorsList = doctorDAO.findDoctorList(criteria);

        // 1개 더 가져왔으면 마지막 제거
        boolean hasMore = doctorsList.size() > criteria.getRowCount();
        if(hasMore) {
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
        int total = doctorDAO.findCountAllStatus(search);
        Criteria criteria = new Criteria(search.getPage(), total);
        List<DoctorDTO> doctorsList = doctorDAO.findAllStatus(criteria,doctorStatus,search);
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
}