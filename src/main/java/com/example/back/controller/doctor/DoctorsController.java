package com.example.back.controller.doctor;

import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors/list")
@RequiredArgsConstructor
public class DoctorsController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<DoctorListCriteriaDTO> getDoctorList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam Long currentMemberId) {
        try {
            DoctorListCriteriaDTO listDTO = doctorService.getList(page, currentMemberId);
            return ResponseEntity.ok(listDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}