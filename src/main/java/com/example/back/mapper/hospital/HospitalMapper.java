package com.example.back.mapper.hospital;

import com.example.back.domain.hospital.HospitalVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HospitalMapper {
    public void insertHospital(HospitalVO hospitalVO);
}
