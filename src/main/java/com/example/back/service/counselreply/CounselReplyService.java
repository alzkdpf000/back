package com.example.back.service.counselreply;

import com.example.back.domain.counselreply.CounselReplyVO;
import com.example.back.dto.counselreply.CounselReplyDTO;

public interface CounselReplyService {

    default CounselReplyVO toVO(CounselReplyDTO counselReplyDTO) {
        return CounselReplyVO.builder()
                .id(counselReplyDTO.getId())
                .counselReplyAcceptance(counselReplyDTO.getCounselReplyAcceptance())
                .createdDatetime(counselReplyDTO.getCreatedDatetime())
                .counselReplyStatus(counselReplyDTO.getCounselReplyStatus())
                .counselReplyContent(counselReplyDTO.getCounselReplyContent())
                .consultationPostId(counselReplyDTO.getConsultationPost() != null ? counselReplyDTO.getConsultationPost().getId() : null)
                .doctorId(counselReplyDTO.getDoctorId())
                .updatedDatetime(counselReplyDTO.getUpdatedDatetime())
                .build();
    }
}
