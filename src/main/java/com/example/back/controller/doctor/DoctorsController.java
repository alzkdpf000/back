package com.example.back.controller.doctor;


import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorsController {
    private final DoctorService doctorService;
    private final DoctorListCriteriaDTO doctorListCriteriaDTO;

    //    목록
    @GetMapping("/list/{page}")
    public ResponseEntity<?> list(@PathVariable("page") int page) {
        DoctorListCriteriaDTO doctorListCriteriaDTO = doctorService.getList(page);
        if (doctorListCriteriaDTO == null || doctorListCriteriaDTO.getDoctorsList().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(doctorListCriteriaDTO);
        }
        return ResponseEntity.ok(doctorListCriteriaDTO);
    }
}











