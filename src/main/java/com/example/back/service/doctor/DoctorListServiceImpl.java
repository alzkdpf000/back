//package com.example.back.service.doctor;
//
//import com.example.back.dto.doctor.DoctorListCriteriaDTO;
//import com.example.back.dto.doctor.DoctorListDTO;
//import com.example.back.repository.doctor.DoctorListDAO;
//import com.example.back.util.Criteria;
//import com.example.back.util.DateUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Primary
//public class DoctorListServiceImpl implements DoctorListService {
//    private final DoctorListDAO doctorListDAO;
//
//    @Override
//    public DoctorListCriteriaDTO getList(int page) {
//        DoctorListCriteriaDTO doctorListCriteriaDTO = new DoctorListCriteriaDTO();
//        Criteria criteria = new Criteria(page, doctorListDAO.findCountDoctorList());
//        List<DoctorListDTO> doctorLists = doctorListDAO.findDoctorList(criteria);
//
//        criteria.setHasMore(doctorLists.size() > criteria.getRowCount());
//
////        11개 가져왔으면, 마지막 1개 삭제
//        if(criteria.isHasMore()){
//            doctorLists.remove(doctorLists.size() - 1);
//        }
//
//        doctorListCriteriaDTO.setDoctorLists(doctorLists);
//        doctorListCriteriaDTO.setCriteria(criteria);
//        return doctorListCriteriaDTO;
//    }
//}