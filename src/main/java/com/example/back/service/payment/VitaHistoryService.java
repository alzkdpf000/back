package com.example.back.service.payment;


import com.example.back.domain.payment.VitaHistoryVO;
import com.example.back.dto.payment.VitaHistoryCriteriaDTO;
import com.example.back.dto.payment.VitaHistoryDTO;
import com.example.back.util.Search;

public interface VitaHistoryService {

    //    관리자 페이지 충전 사용 내역들
    public VitaHistoryCriteriaDTO getVitaHistories(Search search);




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
                .vitaHistoryType(vitaHistoryDTO.getVitaHistoryType())
                .build();
    }
}
