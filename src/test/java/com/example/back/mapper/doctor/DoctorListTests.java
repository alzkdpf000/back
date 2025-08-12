package com.example.back.mapper.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.service.doctor.DoctorListService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class DoctorListTests {

    @Autowired
    private DoctorListService doctorListService;

    @Test
    public void testGetList() {
        DoctorListDTO doctorListDTO = new DoctorListDTO();


    }
}