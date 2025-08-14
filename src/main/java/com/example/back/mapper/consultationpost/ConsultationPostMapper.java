package com.example.back.mapper.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultationPostMapper {
    //    조회순(인기순)5개 게시글 조회 현재 임시로 5개만
    List<ConsultationPostCategoryFileUserDTO> select5OrderByViewCountDesc(int limit,int offset);
}
