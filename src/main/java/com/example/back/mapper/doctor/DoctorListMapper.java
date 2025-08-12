package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorListMapper {
//  의사 리스트
    public List<DoctorListDTO> selectDoctorList();
}
