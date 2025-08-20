package com.example.back.service.vitahistory;


import com.example.back.domain.vitahistory.VitaHistoryVO;
import com.example.back.dto.vitahistory.VitaHistoryDTO;

public interface VitaHistoryService {




    default VitaHistoryVO toVO(VitaHistoryDTO vitaHistoryDTO){
        return VitaHistoryVO.builder()
                .id(vitaHistoryDTO.getId())
                .memberId(vitaHistoryDTO.getMemberId())
                .vitaHistoryAmount(vitaHistoryDTO.getVitaHistoryAmount())
                .vitaHistoryDescription(vitaHistoryDTO.getVitaHistoryDescription())
                .vitaHistoryStatus(vitaHistoryDTO.getVitaHistoryStatus())
                .createdDatetime(vitaHistoryDTO.getCreatedDatetime())
                .updatedDatetime(vitaHistoryDTO.getUpdatedDatetime())
                .vitaHistoryResult(vitaHistoryDTO.getVitaHistoryResult())
                .paymentId(vitaHistoryDTO.getPaymentId())
                .vitaHistoryType(vitaHistoryDTO.getVitaHistoryType())
                .build();
    }
}
