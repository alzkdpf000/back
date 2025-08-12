package com.example.back.service.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorListService {
//  의사 정보 페이지, 5개 노출

    List<DoctorListDTO> selectDoctorList();

    default DoctorListDTO toVO(DoctorListDTO doctorListDTO){
        return DoctorListDTO.builder()
                .memberName(doctorListDTO.getMemberName())
                .doctorSpecialty(doctorListDTO.getDoctorSpecialty())
                .doctorStatus(doctorListDTO.getDoctorStatus())
                .hospitalName(doctorListDTO.getHospitalName())
                .hospitalPhone(doctorListDTO.getHospitalPhone())
                .hospitalAddress(doctorListDTO.getHospitalAddress())
                .createdDate(doctorListDTO.getCreatedDate())
                .updatedDate(doctorListDTO.getUpdatedDate())
                .build();
    }
}
