package com.example.back.service.doctor;

import com.example.back.domain.doctor.DoctorVO;
import com.example.back.dto.doctor.DoctorCriteriaDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.dto.doctor.DoctorListDTO;

import java.util.Optional;

public interface DoctorService {
    //  목록
    public DoctorListCriteriaDTO getList(int page);

    // 멤버 Status 상관없이 목록 출력
    public DoctorCriteriaDTO getListAllStatus(int page,String doctorStatus);

    //  관리자페이지 의사 상세 보기
    public Optional<DoctorHospitalDTO> getDoctorAdminById(Long doctorId);

    //  의사 가입 승인
    public boolean approve(Long doctorId);

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
