package com.example.back.controller.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.service.hospital.HospitalService;
import com.example.back.service.member.MemberService;
import com.example.back.util.Search;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final MemberService memberService;
    private final HospitalService hospitalService;

    // 의사 목록 페이지
    @GetMapping("list/{page}")
    public String listPage(@PathVariable int page,
                           Model model,
                           @RequestParam(required = false) Search search) {

        model.addAttribute("pageTitle", "의사 목록");
        model.addAttribute("search", search);
        return "doctor/doctor-list";
    }


//    의사 회원가입
    @GetMapping("join")
    public String goToJoinForm(DoctorDTO doctorDTO, Model model) {
        model.addAttribute("doctorDTO",doctorDTO);
        return "doctor/joindoctor";
    }
//    의사 회원가입 처리
    @PostMapping("join")
    public RedirectView joinFull(@ModelAttribute DoctorDTO doctorDTO,
                                 @ModelAttribute MemberDTO memberDTO,
                                 @ModelAttribute HospitalDTO hospitalDTO) {

        memberDTO.setMemberPhone(doctorDTO.getMemberPhone());
        hospitalDTO.setHospitalPhone(doctorDTO.getHospitalPhone());

        doctorService.join(doctorDTO, memberDTO, hospitalDTO);

        return new RedirectView("/doctor/login");
    }



//    로그인 페이지 이동
    @GetMapping("login")
    public String goToLoginForm(DoctorDTO doctorDTO ,Model model) {
        model.addAttribute("doctorDTO",doctorDTO);
        return "doctor/login";
    }

}