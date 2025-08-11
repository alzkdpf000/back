package com.example.back.repository.category;

import com.example.back.mapper.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryDAO {
    private final CategoryMapper categoryMapper;
    // 상담글 번호로 해당 상담글 카테고리 목록 조회
    public List<String> findCategoryByPostId(Long consultationPostId) {
        return categoryMapper.selectCategoryByPostId(consultationPostId);
    }
}
