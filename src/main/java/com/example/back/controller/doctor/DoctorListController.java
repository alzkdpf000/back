package com.example.back.controller.doctor;

import com.example.back.service.doctor.DoctorListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor/**")
public class DoctorListController{
    private final DoctorListService doctorListService;

    //    의사 목록
    @GetMapping("list/{page}")
    public String list(@PathVariable int page, Model model){
        model.addAttribute("doctorsListCriteriaDTO", doctorListService.getList(page));
        return "doctor/doctor-list";
    }

}

