package com.example.back.repository.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.mapper.consultationpost.ConsultationPostMapper;
import com.example.back.util.ScrollCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsultationPostDAO {
    private final ConsultationPostMapper consultationPostMapper;

    //    조회순(인기순)게시글 조회
    public List<ConsultationPostCategoryFileUserDTO> find5OrderByViewCountDesc(ScrollCriteria scrollCriteria) {
        return consultationPostMapper.select5OrderByViewCountDesc(scrollCriteria);
    }
    // 특정 회원이 최근에 작성한 게시글 3개 조회
    public List<ConsultationPostDTO> findTop3ByMemberId(Long memberId) {
        return consultationPostMapper.selectTop3ByMemberId(memberId);
    }

}
