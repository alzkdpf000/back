package com.example.back.controller.mainpage;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.service.category.CategoryService;
import com.example.back.service.consultationpost.ConsultationPostService;
import com.example.back.service.file.FileConsultationPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/**")
public class MainPageRestController {
    private final CategoryService categoryService;
    private final FileConsultationPostService fileConsultationPostService;
    private final ConsultationPostService consultationPostService;


//  조회순으로 5개씩 무한 스크롤로 뿌려주기
    @GetMapping("{offset}")
    public ResponseEntity<List<ConsultationPostCategoryFileUserDTO>> mainPage(@PathVariable int offset) {
        List<ConsultationPostCategoryFileUserDTO> consultationPostService5PostsByViews = consultationPostService.get5PostsByViews(offset);
        Long consultationPostId = 0L;
        List<String> categories = null;
        List<String> files = null;
        for (ConsultationPostCategoryFileUserDTO top5PostsByView : consultationPostService5PostsByViews) {
            consultationPostId = top5PostsByView.getId();
            categories = categoryService.getCategoryByPostId(consultationPostId);
            top5PostsByView.setCategories(categories);
            files = fileConsultationPostService.getFilesByPostId(consultationPostId);
            top5PostsByView.setConsultationPostFiles(files);
        }
//        consultationPostService5PostsByViews.stream().map(ConsultationPostCategoryFileUserDTO::toString).forEach(log::info);
        if(consultationPostService5PostsByViews.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(consultationPostService5PostsByViews);
        }
        return ResponseEntity.ok(consultationPostService5PostsByViews);
    }
}
