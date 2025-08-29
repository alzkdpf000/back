package com.example.back.controller.doctor;

import com.example.back.common.enumeration.Role;
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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final MemberService memberService;
    private final HospitalService hospitalService;

    // 의사 전체 목록 페이지
    @GetMapping("/list")
    public String listPage() {
        return "doctor/doctor-list";
    }

    // 의사 상세보기 페이지
    @GetMapping("/detail/{doctorId}")
    public String doctorDetailPage(@PathVariable Long doctorId, Model model) {
        model.addAttribute("doctorId", doctorId);
        return "doctor/doctor-detail";
    }


//    의사 회원가입
    @GetMapping("join")
    public String goToJoinForm(DoctorDTO doctorDTO, Model model,  MemberDTO memberDTO) {
        model.addAttribute("doctorDTO",doctorDTO);
        model.addAttribute("memberDTO",memberDTO);
        log.info("의사회원가입{} = ", doctorDTO);

        if (memberDTO == null || memberDTO.getKakaoEmail() == null) {
            memberDTO = new MemberDTO();
        }
        model.addAttribute("member",memberDTO);
        log.info("의사회원가입={}", doctorDTO);
        return "doctor/joindoctor";
    }
//    의사 회원가입 처리
    @PostMapping("join")
    public RedirectView joinFull(@ModelAttribute DoctorDTO doctorDTO,
                                 @ModelAttribute MemberDTO memberDTO,
                                 @ModelAttribute HospitalDTO hospitalDTO) {

        memberDTO.setMemberPhone(doctorDTO.getMemberPhone());
        hospitalDTO.setHospitalPhone(doctorDTO.getHospitalPhone());

        memberDTO.setMemberRole(Role.DOCTOR);

        doctorService.join(doctorDTO, memberDTO, hospitalDTO);

        return new RedirectView("/doctor/login");
    }



//    로그인 페이지 이동
    @GetMapping("login")
    public String goToLoginForm(DoctorDTO doctorDTO ,Model model) {
        model.addAttribute("doctorDTO",doctorDTO);
        return "doctor/login";
    }


    //    이메일 중복 검사
    @PostMapping("check-email")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestBody Map<String, String> member) {
        String memberEmail = member.get("memberEmail");
        boolean isExist = memberService.isExistMemberEmail(memberEmail);
        Map<String, Object> result = new HashMap<>();
        result.put("memberEmail", memberEmail);
        result.put("isExist", isExist);

        return result;
    }

}