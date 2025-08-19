package com.example.back.service.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.domain.doctor.DoctorVO;
import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.doctor.DoctorCriteriaDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.util.Search;

import java.util.Optional;

public interface DoctorService {
    //  목록
    DoctorListCriteriaDTO getList(int page, Long currentMemberId);

    // 멤버 Status 상관없이 목록 출력
    public DoctorCriteriaDTO getListAllStatus(Search search, String doctorStatus);

    //  관리자페이지 의사 상세 보기
    public Optional<DoctorHospitalDTO> getDoctorAdminById(Long doctorId);

    //  의사 가입 승인
    public boolean approve(Long doctorId);

//    의사 회원가입
    public void join(DoctorDTO doctorDTO, MemberDTO memberDTO, HospitalDTO hospitalDTO);


    default DoctorVO gtoDoctorVO(DoctorListDTO doctorListDTO){
        return DoctorVO.builder()

                .id(doctorListDTO.getId())
                .doctorSpecialty(doctorListDTO.getDoctorSpecialty())
                .doctorStatus(doctorListDTO.getDoctorStatus())
                .hospitalId(doctorListDTO.getHospitalId())
                .hospitalName(doctorListDTO.getHospitalName())
                .hospitalPhone(doctorListDTO.getHospitalPhone())
                .hospitalAddress(doctorListDTO.getHospitalAddress())
                .createdDatetime(doctorListDTO.getCreatedDatetime())
                .updatedDatetime(doctorListDTO.getUpdatedDatetime())
                .build();


    }

    default DoctorVO gtoDoctorVO(DoctorDTO doctorDTO){
        return DoctorVO.builder()
                .id(doctorDTO.getId())
                .doctorSpecialty(doctorDTO.getDoctorSpecialty())
                .doctorStatus(doctorDTO.getDoctorStatus())
                .id(doctorDTO.getMemberId())
                .hospitalId(doctorDTO.getHospitalId())
                .hospitalName(doctorDTO.getHospitalName())
                .hospitalPhone(doctorDTO.getHospitalPhone())
                .createdDatetime(doctorDTO.getCreatedDatetime())
                .updatedDatetime(doctorDTO.getUpdatedDatetime())

                .build();
    }
}
