package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DoctorMapperTests {

    @Autowired
    private DoctorMapper doctorMapper;

    @Test
    public void testInsertDoctor(){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorLicenseNumber("!23434");
        doctorDTO.setDoctorSpecialty("내과");
        doctorDTO.setHospitalId(2L);
        doctorDTO.setId(1L);

//        doctorMapper.insertDoctor(doctorDTO);
//        log.info("Doctor Inserted: {}", doctorDTO);


    }

}
