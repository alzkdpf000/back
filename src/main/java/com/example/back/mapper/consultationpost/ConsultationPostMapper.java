package com.example.back.mapper.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.util.ScrollCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultationPostMapper {
    //    조회순(인기순)5개 게시글 조회 현재 임시로 5개만
    public List<ConsultationPostCategoryFileUserDTO> select5OrderByViewCountDesc(ScrollCriteria scrollCriteria);

    // 특정 회원이 최근에 작성한 게시글 3개 조회
    public List<ConsultationPostDTO> selectTop3ByMemberId(Long memberId);
}
