package com.example.back.repository.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.mapper.doctor.DoctorMapper;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DoctorDAO {
    private final DoctorMapper doctorMapper;
    private final DoctorDTO doctorDTO;

//  전체 리스트 조회
    public List<DoctorListDTO> findDoctorList(Criteria criteria, Search search, Long currentMemberId) {
    criteria.setCurrentMemberId(currentMemberId);
    return doctorMapper.selectDoctorList(criteria, search, currentMemberId);
    }

//  검색 조건 포함 전체 개수 조회
    public int findCountDoctorList(Search search){
        return doctorMapper.selectCountAll(search);
    }

    public DoctorListDTO findDoctorDetailById(Long doctorId,Long currentMemberId) {
        // Mapper 호출 시 필요하면 로그인 회원 ID 전달
        return doctorMapper.selectDoctorDetailById(doctorId, currentMemberId);
    }

    //  멤버 status 상관없는 의사 목록
    public List<DoctorDTO> findAllStatus(Criteria criteria, String doctorStatus, Search search) {
        return doctorMapper.selectDoctorsByStatus(criteria,doctorStatus,search);
    }
    //  멤버 status 상관없는 의사 전체 수
    public int findCountAllStatus(Search search,String doctorStatus){
        return doctorMapper.selectDoctorCountByStatus(search,doctorStatus);
    }
    //  관리자페이지 의사 상세 보기
    public Optional<DoctorHospitalDTO> findDoctorById(Long doctorId){
        return doctorMapper.selectDoctorById(doctorId);
    }
    //  의사 가입 승인
    public int approveDoctor(Long doctorId){
        return doctorMapper.updateDoctorStatusToApproved(doctorId);
    }

//    의사 회원가입 회원추가
    public void insertJoinDoctor(DoctorDTO doctorDTO){
        doctorMapper.insertJoinDoctor(doctorDTO);
    }
}
