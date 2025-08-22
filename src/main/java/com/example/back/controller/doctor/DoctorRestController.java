package com.example.back.controller.doctor;

import com.example.back.dto.doctor.DoctorDetailCriteriaDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorRestController {

    private final DoctorService doctorService;

//  의사 전체목록 조회
    @GetMapping("/list/{page}")
    public ResponseEntity<DoctorListCriteriaDTO> getDoctorList(
            @PathVariable int page,
            @ModelAttribute Search search){
        System.out.println(Arrays.toString(search.getCategories()));
        try {
            DoctorListCriteriaDTO listDTO = doctorService.getList(page, search);
            return ResponseEntity.ok(listDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

//  의사 상세보기 페이지
    @GetMapping("/detail/{doctorId}/{page}")
    public ResponseEntity<DoctorDetailCriteriaDTO> getDoctorDetail(
            @PathVariable Long doctorId,
            @PathVariable int page
    ) {
        try {
            DoctorDetailCriteriaDTO detailDTO = doctorService.getDoctorDetail(doctorId, page);
            return ResponseEntity.ok(detailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
