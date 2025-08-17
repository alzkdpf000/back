package com.example.back.service.doctor;

import com.example.back.domain.doctor.DoctorVO;
import com.example.back.dto.doctor.DoctorCriteriaDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.dto.doctor.DoctorListDTO;

public interface DoctorListService {
    //  목록
    public DoctorListCriteriaDTO getList(int page);

    // 멤버 Status 상관없이 목록 출력
    public DoctorCriteriaDTO getListAllStatus(int page);

    default DoctorVO gtoDoctorVO(DoctorListDTO doctorListDTO){
        return DoctorVO.builder()
                .id(doctorListDTO.getId())
                .doctorSpecialty(doctorListDTO.getDoctorSpecialty())
                .doctorStatus(doctorListDTO.getDoctorStatus())
                .id(doctorListDTO.getMemberId())
                .hospitalId(doctorListDTO.getHospitalId())
                .hospitalName(doctorListDTO.getHospitalName())
                .hospitalPhone(doctorListDTO.getHospitalPhone())
                .hospitalAddress(doctorListDTO.getHospitalAddress())
                .createdDatetime(doctorListDTO.getCreatedDatetime())
                .updatedDatetime(doctorListDTO.getUpdatedDatetime())
                .build();
    }
}
