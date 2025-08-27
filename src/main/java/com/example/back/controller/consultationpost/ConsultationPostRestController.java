package com.example.back.controller.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCriteriaDTO;
import com.example.back.dto.doctor.DoctorListCriteriaDTO;
import com.example.back.service.consultationpost.ConsultationPostService;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/posts")
public class ConsultationPostRestController {
    private final ConsultationPostService consultationPostService;

    @GetMapping("/list/{page}")
    public ResponseEntity<ConsultationPostCriteriaDTO> getPostList(
            @PathVariable int page,
            @ModelAttribute Search search) {
        try {
            if (search.getCategories() == null) search.setCategories(new String[0]);
            if (search.getKeyword() == null) search.setKeyword("");
            System.out.println("categories: " + Arrays.toString(search.getCategories()));
            System.out.println("keyword: " + search.getKeyword());

            ConsultationPostCriteriaDTO listDTO = consultationPostService.getPostList(page, search);
            return ResponseEntity.ok(listDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
