package com.example.back.repository.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.mapper.doctor.DoctorListMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorListDAO {
    private final DoctorListMapper doctorListMapper;

    public int findCountDoctorList(){
        return doctorListMapper.selectCountAll();
    }

    public List<DoctorListDTO> findDoctorList(Criteria criteria) {
        return doctorListMapper.selectDoctorList(criteria);
    }

    //  멤버 status 상관없는 의사 목록
    public List<DoctorDTO> findAllStatus(Criteria criteria) {
        return doctorListMapper.selectAllStatus(criteria);
    }
    //  멤버 status 상관없는 의사 전체 수
    public int findCountAllStatus(){
        return doctorListMapper.selectCountAllStatus();
    }
}
