package com.example.back.mapper.category;

import com.example.back.repository.category.CategoryDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CategoryTests {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryDAO categoryDAO;


    @Test
    public void testMapperSelectFilesByPostId(){
        List<String> categories = categoryMapper.selectCategoryByPostId(1L);
        categories.forEach(log::info);
    }

    @Test
    public void testDAOFindCategoryByPostId(){
        List<String> categories = categoryDAO.findCategoryByPostId(1L);
        categories.forEach(log::info);
    }
}
