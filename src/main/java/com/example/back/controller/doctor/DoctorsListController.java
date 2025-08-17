package com.example.back.controller.doctor;


import com.example.back.dto.member.MemberDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.service.doctor.DoctorListService;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.service.doctor.DoctorListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorsListController {
    private final DoctorListService doctorListService;
    private final DoctorListCriteriaDTO doctorListCriteriaDTO;

    //    목록
    @GetMapping("/list/{page}")
    public ResponseEntity<?> list(@PathVariable("page") int page) {
        DoctorListCriteriaDTO doctorListCriteriaDTO = doctorListService.getList(page);
        if (doctorListCriteriaDTO == null || doctorListCriteriaDTO.getDoctorsList().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(doctorListCriteriaDTO);
        }
        return ResponseEntity.ok(doctorListCriteriaDTO);
    }
}











