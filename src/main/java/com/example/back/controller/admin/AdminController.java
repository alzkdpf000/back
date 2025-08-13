package com.example.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/**")
public class AdminController {

    @GetMapping("/")
    public String goAdminPage(){
        return "/admin/mainpage";
    }
    @GetMapping
    public String goAdmin(){
        return "/admin/inquiry";
    }
}
