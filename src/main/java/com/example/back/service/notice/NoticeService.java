package com.example.back.service.notice;


import com.example.back.domain.notice.NoticeVO;
import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.dto.notice.NoticesCriteria;

import java.util.List;

public interface NoticeService {
    NoticesCriteria getList(int page);

    default NoticeVO toVO(NoticeDTO noticeDTO){
        return NoticeVO.builder()
                .id(noticeDTO.getId())
                .noticesTitle(noticeDTO.getNoticesTitle())
                .noticesContent(noticeDTO.getNoticesTitle())
                .noticeStatus(noticeDTO.getNoticeStatus())
                .memberId(noticeDTO.getMemberId())
                .createdDatetime(noticeDTO.getCreatedDatetime())
                .updatedDatetime(noticeDTO.getUpdatedDatetime())
                .build();
    }
}
