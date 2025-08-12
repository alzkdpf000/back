package com.example.back.service.category;

import com.example.back.mapper.category.CategoryMapper;
import com.example.back.repository.category.CategoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;

    @Override
//    @Transactional(readOnly = true)
    // 상담글 번호로 해당 상담글 카테고리 목록 조회
    public List<String> getCategoryByPostId(Long consultationPostId) {
        return categoryDAO.findCategoryByPostId(consultationPostId);
    }




}
