package com.example.back.controller.doctor;

import com.example.back.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    //    의사 목록
    @GetMapping("/list/{page}")
    public String list(@PathVariable int page, Model model) {
        model.addAttribute("doctorsListCriteriaDTO", doctorService.getList(page));
        return "doctor/doctor-list";
    }

}