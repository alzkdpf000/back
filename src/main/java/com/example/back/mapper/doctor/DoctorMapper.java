package com.example.back.mapper.doctor;

import com.example.back.domain.doctor.DoctorVO;
import com.example.back.dto.doctor.DoctorDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper {

//    회원가입 추가
    public void insertDoctor(DoctorDTO doctorDTO);

}
