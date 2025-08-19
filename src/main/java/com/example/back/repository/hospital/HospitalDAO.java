package com.example.back.repository.hospital;

import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.domain.hospital.HospitalVO;
import com.example.back.mapper.hospital.HospitalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HospitalDAO {
    private final HospitalMapper hospitalMapper;

    public void save(HospitalVO hospitalVO) {
        hospitalMapper.insertHospital(hospitalVO);
    }
}
