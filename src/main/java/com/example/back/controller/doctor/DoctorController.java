package com.example.back.controller.doctor;

import com.example.back.common.enumeration.Status;
import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.dto.doctor.DoctorDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.service.doctor.DoctorService;
import com.example.back.service.hospital.HospitalService;
import com.example.back.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final MemberService memberService;
    private final HospitalService hospitalService;
    private final MemberDTO memberDTO;
    private final DoctorDTO doctorDTO;

    // 의사 목록 페이지
    @GetMapping("/list")
    public String listPage(Model model) {

        model.addAttribute("pageTitle", "의사 목록");
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
    public RedirectView joinFull(MemberDTO memberDTO, HospitalDTO hospitalDTO, DoctorDTO doctorDTO) {

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