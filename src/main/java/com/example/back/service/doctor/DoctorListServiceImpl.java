package com.example.back.service.doctor;

import com.example.back.dto.doctor.DoctorCriteriaDTO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.repository.doctor.DoctorListDAO;
import com.example.back.service.doctor.DoctorListService;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DoctorListServiceImpl implements DoctorListService {
    private final DoctorListDAO doctorListDAO;

    @Override
    public DoctorListCriteriaDTO getList(int page) {
        DoctorListCriteriaDTO doctorListCriteriaDTO = new DoctorListCriteriaDTO();
        Criteria criteria = new Criteria(page, doctorListDAO.findCountDoctorList());
        List<DoctorListDTO> doctorsList = doctorListDAO.findDoctorList(criteria);

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
    public DoctorCriteriaDTO getListAllStatus(int page) {
        DoctorCriteriaDTO doctorCriteriaDTO = new DoctorCriteriaDTO();
        int total = doctorListDAO.findCountAllStatus();
        Criteria criteria = new Criteria(page, total);
        List<DoctorDTO> doctorsList = doctorListDAO.findAllStatus(criteria);
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
}