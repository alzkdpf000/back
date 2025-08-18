package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorListMapper {
//  추가
    public void insertDoctor(DoctorListDTO doctorListDTO);

//  목록
    public List<DoctorListDTO> selectDoctorList(Criteria criteria);

//  전체 개수
    public int selectCountAll();

    //    회원가입 추가
    public void insertDoctor(DoctorDTO doctorDTO);
}
