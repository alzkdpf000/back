package com.example.back.repository.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.mapper.doctor.DoctorListMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
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
}
