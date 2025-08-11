package com.example.back.controller.main;

import com.example.back.repository.category.CategoryDAO;
import com.example.back.service.category.CategoryService;
import com.example.back.service.consultationpost.ConsultationPostService;
import com.example.back.service.file.FileConsultationPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final CategoryService categoryService;
    private final FileConsultationPostService fileConsultationPostService;
    private final ConsultationPostService consultationPostService;


    @GetMapping
    public String index(){
        return "/main/main";

    }
}
