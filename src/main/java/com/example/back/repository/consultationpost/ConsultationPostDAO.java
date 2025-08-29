package com.example.back.repository.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.mapper.consultationpost.ConsultationPostMapper;
import com.example.back.util.Criteria;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsultationPostDAO {
    private final ConsultationPostMapper consultationPostMapper;

    //    조회순(인기순)으로 QnA 가져오기
    public List<ConsultationPostCategoryFileUserDTO> findOrderByViewCountDesc(ScrollCriteria scrollCriteria) {
        return consultationPostMapper.selectOrderByViewCountDesc(scrollCriteria);
    }
    // 특정 회원이 최근에 작성한 게시글 3개 조회
    public List<ConsultationPostDTO> findTop3ByMemberId(Long memberId) {
        return consultationPostMapper.selectTop3ByMemberId(memberId);
    }

    //  전체 리스트 조회 + 검색조건
    public List<ConsultationPostCategoryFileUserDTO> findPostList(Criteria criteria, Search search, String orderBy) {
        return consultationPostMapper.selectPostList(criteria, search, orderBy);
    }

    // 전체 개수 조회 + 검색조건
    public int findCountPostList(Search search){
        return consultationPostMapper.selectCountAll(search);
    }

    public List<String> findCategoryNamesByPostId(Long postId) {
        return consultationPostMapper.selectCategoryNamesByPostId(postId);
    }

    // 조회수 업데이트
    public void increaseViewCount(Long postId) {
        consultationPostMapper.increaseViewCount(postId);
    }

}
