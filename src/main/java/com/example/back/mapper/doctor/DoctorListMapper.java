package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DoctorListMapper {
    //  추가
    public void insertDoctor(DoctorListDTO doctorListDTO);

    //  목록
    public List<DoctorListDTO> selectDoctorList(Criteria criteria);

    //  전체 개수
    public int selectCountAll();


    //  멤버 status 상관없는 의사 목록
    public List<DoctorDTO> selectDoctorsByStatus(@Param("criteria") Criteria criteria, @Param("doctorStatus") String doctorStatus);

    //  멤버 status 상관없는 의사 전체 수
    public int selectDoctorCountByStatus(@Param("doctorStatus") String doctorStatus);

    //  관리자페이지 의사 상세 보기
    public Optional<DoctorHospitalDTO> selectDoctorById(Long doctorId);

    //  의사 가입 승인
    public int updateDoctorStatusToApproved(Long doctorId);
}
