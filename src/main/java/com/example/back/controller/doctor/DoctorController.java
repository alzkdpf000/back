package com.example.back.controller.doctor;

import com.example.back.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    // 의사 목록 페이지
    @GetMapping("/list")
    public String listPage(Model model) {

        model.addAttribute("pageTitle", "의사 목록");
        return "doctor/doctor-list";
    }
}