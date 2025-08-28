package com.example.back.service.consultationpost;

import com.example.back.domain.consultationpost.ConsultationPostVO;
import com.example.back.dto.consultationpost.ConsultationPostCriteriaDTO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultationPostService {
    //    조회순(인기순)5개 게시글 조회 현재 임시로 3개만
    ConsultationPostCriteriaDTO get5PostsByViews(int page);

    // 전체 목록 조회 (검색 없음)
    ConsultationPostCriteriaDTO getPostList(int page);

    // 전체 목록 조회 (검색 조건)
    ConsultationPostCriteriaDTO getPostList(int page, Search search, String order);

    // 게시글 ID로 카테고리 이름 조회

    default ConsultationPostVO toVO(ConsultationPostDTO consultationPostDTO) {
        return ConsultationPostVO.builder()
                .id(consultationPostDTO.getId())
                .consultationPostTitle(consultationPostDTO.getConsultationPostTitle())
                .consultationPostContent(consultationPostDTO.getConsultationPostContent())
                .consultationPostViewCount(consultationPostDTO.getConsultationPostViewCount())
                .consultationPostStatus(consultationPostDTO.getConsultationPostStatus())
                .createdDatetime(consultationPostDTO.getCreatedDatetime())
                .updatedDatetime(consultationPostDTO.getUpdatedDatetime())
                .memberId(consultationPostDTO.getMemberId())
                .build();
    }

}
