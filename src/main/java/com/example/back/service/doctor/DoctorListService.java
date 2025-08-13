package com.example.back.service.doctor;

import com.example.back.domain.doctor.DoctorVO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.dto.doctor.DoctorListDTO;


public interface DoctorListService {
    //  목록
    public DoctorListCriteriaDTO getList(int page);


    default DoctorVO toDoctorVO(DoctorListDTO doctorListDTO){
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
