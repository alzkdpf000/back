package com.example.back.service.consultationpost;

import com.example.back.domain.consultationpost.ConsultationPostVO;
import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostDTO;

import java.util.List;

public interface ConsultationPostService {
    //    조회순(인기순)5개 게시글 조회 현재 임시로 3개만
    List<ConsultationPostCategoryFileUserDTO> getTop5PostsByViews();

    default ConsultationPostVO toVO(ConsultationPostDTO consultationPostDTO) {
        return ConsultationPostVO.builder()
                .id(consultationPostDTO.getId())
                .consultationPostTitle(consultationPostDTO.getConsultationPostTitle())
                .consultationPostContent(consultationPostDTO.getConsultationPostContent())
                .consultationPostViewCount(consultationPostDTO.getConsultationPostViewCount())
                .consultationPostStatus(consultationPostDTO.getConsultationPostStatus())
                .createdDate(consultationPostDTO.getCreatedDate())
                .updatedDate(consultationPostDTO.getUpdatedDate())
                .memberId(consultationPostDTO.getMemberId())
                .build();
    }
}
