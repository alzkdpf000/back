package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DoctorListMapper {
//  추가
    public void insertDoctor(DoctorListDTO doctorListDTO);

//  목록
    public List<DoctorListDTO> selectDoctorList(Criteria criteria);

//  전체 개수
    public int selectCountAll();


//  멤버 status 상관없는 의사 목록
    List<DoctorDTO> selectAllStatus(@Param("criteria") Criteria criteria);
//  멤버 status 상관없는 의사 전체 수
    int selectCountAllStatus();

    List<DoctorHospitalDTO> selectDoctorById(Long doctorId);

}
