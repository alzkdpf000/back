package com.example.back.controller.doctor;

import com.example.back.service.doctor.DoctorListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor/**")
public class DoctorListController{
    private final DoctorListService doctorListService;

    //    의사 목록
    @GetMapping("list")
    public String list(Model model){
//        model.addAttribute("postsCriteriaDTO", postService.getList(page));
        return "doctor-hospital-list";
    }
//    @GetMapping("list/{page}")
//    public String list(@PathVariable int page, Model model){
//        return "doctor/list";  // page별 처리 추가 가능
//    }

}













