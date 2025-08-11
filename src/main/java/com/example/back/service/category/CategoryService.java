package com.example.back.service.category;

import com.example.back.domain.category.CategoryVO;
import com.example.back.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {
// 상담글 번호로 해당 상담글 카테고리 목록 조회
    public List<String> getCategoryByPostId(Long consultationPostId);

    default CategoryVO toVO(CategoryDTO categoryDTO){
        return CategoryVO.builder()
                .id(categoryDTO.getId())
                .categoryName(categoryDTO.getCategoryName())
                .createdDate(categoryDTO.getCreatedDate())
                .updatedDate(categoryDTO.getUpdatedDate())
                .build();
    }
}
