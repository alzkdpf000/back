package com.example.back.mapper.doctor;

<<<<<<< HEAD:src/main/java/com/example/back/mapper/doctor/DoctorListMapper.java
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.doctor.DoctorHospitalDTO;
=======
import com.example.back.domain.doctor.DoctorVO;
import com.example.back.dto.doctor.DoctorDTO;
>>>>>>> 697d8e1f374f0a4649b9bed1063d57270175a7d6:src/main/java/com/example/back/mapper/doctor/DoctorMapper.java
import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DoctorMapper {
//  추가
    public void insertDoctor(DoctorListDTO doctorListDTO);

//  목록
    public List<DoctorListDTO> selectAll(Criteria criteria);

//  전체 개수
    public int selectCountAll();

//  멤버 status 상관없는 의사 목록
    List<DoctorDTO> selectDoctorsByStatus(@Param("criteria") Criteria criteria, @Param("doctorStatus") String doctorStatus);
//  멤버 status 상관없는 의사 전체 수
    int selectDoctorCountByStatus(@Param("doctorStatus") String doctorStatus);
//  관리자페이지 의사 상세 보기
    Optional<DoctorHospitalDTO> selectDoctorById(Long doctorId);
//    회원가입 추가
    public void insertDoctor(DoctorDTO doctorDTO);

}
