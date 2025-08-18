package com.example.back.repository.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.mapper.doctor.DoctorMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorDAO {
    private final DoctorMapper doctorMapper;

    public int findCountDoctorList(){
        return doctorMapper.selectCountAll();
    }

    public List<DoctorListDTO> findDoctorList(Criteria criteria) {
        return doctorMapper.selectAll(criteria);
    }
}
