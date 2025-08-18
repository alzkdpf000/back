package com.example.back.repository.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.mapper.doctor.DoctorMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    //  멤버 status 상관없는 의사 목록
    public List<DoctorDTO> findAllStatus(Criteria criteria, String doctorStatus) {
        return doctorMapper.selectDoctorsByStatus(criteria,doctorStatus);
    }
    //  멤버 status 상관없는 의사 전체 수
    public int findCountAllStatus(String doctorStatus){
        return doctorMapper.selectDoctorCountByStatus(doctorStatus);
    }
    //  관리자페이지 의사 상세 보기
    public Optional<DoctorHospitalDTO> findDoctorById(Long doctorId){
        return doctorMapper.selectDoctorById(doctorId);
    }
}
