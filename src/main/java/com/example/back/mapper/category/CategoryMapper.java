package com.example.back.mapper.category;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // 상담글 번호로 해당 상담글 카테고리 목록 조회
    public List<String> selectCategoryByPostId(Long consultationPostId);
}
