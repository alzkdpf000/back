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
            @RequestParam(required = false, defaultValue = "latest") String order,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String[] categories) {

        try {
            Search search = new Search();
            search.setKeyword(keyword != null ? keyword : "");
            search.setCategories(categories != null ? categories : new String[0]);
            search.setOrderType(order != null ? order : "latest"); // << 이 부분 추가

            ConsultationPostCriteriaDTO listDTO = consultationPostService.getPostList(page, search, order);
            return ResponseEntity.ok(listDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
