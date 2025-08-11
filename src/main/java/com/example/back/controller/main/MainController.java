package com.example.back.controller.main;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.service.category.CategoryService;
import com.example.back.service.consultationpost.ConsultationPostService;
import com.example.back.service.file.FileConsultationPostService;
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
public class MainController {
    private final CategoryService categoryService;
    private final FileConsultationPostService fileConsultationPostService;
    private final ConsultationPostService consultationPostService;


    @GetMapping
    public java.lang.String index(Model model) {
        List<ConsultationPostCategoryFileUserDTO> top5PostsByViews = consultationPostService.getTop5PostsByViews();
        Long consultationPostId = 0L;
        List<java.lang.String> categories = null;
        List<String> files = null;
        for (ConsultationPostCategoryFileUserDTO top5PostsByView : top5PostsByViews) {
            consultationPostId = top5PostsByView.getId();
            categories = categoryService.getCategoryByPostId(consultationPostId);
            top5PostsByView.setCategories(categories);
            files = fileConsultationPostService.getFilesByPostId(consultationPostId);
            top5PostsByView.setPostFiles(files);
        }
        top5PostsByViews.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
        return "/main/main";

    }
}
