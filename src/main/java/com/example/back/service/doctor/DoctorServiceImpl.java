package com.example.back.service.doctor;

import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO;

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
}