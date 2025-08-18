package com.example.back.service.counselreply;

import com.example.back.domain.consultationpost.ConsultationPostVO;
import com.example.back.domain.counselreply.CounselReplyVO;
import com.example.back.dto.consultationpost.ConsultationPostCriteria;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import com.example.back.dto.counselreply.CounselReplyDTO;

public interface CounselReplyService {

    default CounselReplyVO toVO(CounselReplyDTO counselReplyDTO) {
        return CounselReplyVO.builder()
                .id(counselReplyDTO.getId())
                .counselReplyAccpetance(counselReplyDTO.getCounselReplyAccpetance())
                .createdDatetime(counselReplyDTO.getCreatedDatetime())
                .counselReplyStatus(counselReplyDTO.getCounselReplyStatus())
                .counselReplyContent(counselReplyDTO.getCounselReplyContent())
                .consultationPostId(counselReplyDTO.getConsultationPostId())
                .doctorId(counselReplyDTO.getDoctorId())
                .updatedDatetime(counselReplyDTO.getUpdatedDatetime())
                .build();
    }
}
