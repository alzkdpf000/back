package com.example.back.controller.mainpage;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainPageController {
    private final HttpSession session;


    @GetMapping
    public String goMainPage() {
        session.setAttribute("member", "tempMember");
        return "/main/main";
    }

    @GetMapping("service-info")
    public String goServiceInfoPage() {
        return "/member/service-info";
    }
}
