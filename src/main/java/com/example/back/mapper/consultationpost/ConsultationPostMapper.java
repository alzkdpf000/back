package com.example.back.mapper.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.util.Criteria;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsultationPostMapper {
    //    조회순(인기순)5개 게시글 조회 현재 임시로 5개만
    public List<ConsultationPostCategoryFileUserDTO> select5OrderByViewCountDesc(ScrollCriteria scrollCriteria);

    // 특정 회원이 최근에 작성한 게시글 3개 조회
    public List<ConsultationPostDTO> selectTop3ByMemberId(Long memberId);

    // 특정 게시글의 카테고리 이름 조회
    public List<String> selectCategoryNamesByPostId(Long postId);

    // 전체 게시글 조회
    List<ConsultationPostCategoryFileUserDTO> selectPostList(
            @Param("criteria") Criteria criteria,
            @Param("search") Search search,
            @Param("orderBy") String orderBy
    );
    
    // 전체 게시글 개수 조회
    public int selectCountAll(@Param("search") Search search);

    // 조회수 증가
    public void increaseViewCount(Long postId);
}
