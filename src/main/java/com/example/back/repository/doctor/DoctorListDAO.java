package com.example.back.repository.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.mapper.doctor.DoctorListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorListDAO {
    private final DoctorListMapper doctorListMapper;

    public List<DoctorListDTO> getList(DoctorListDTO doctorListDTO) {
        return doctorListMapper.selectDoctorList(doctorListDTO);
    }
}
