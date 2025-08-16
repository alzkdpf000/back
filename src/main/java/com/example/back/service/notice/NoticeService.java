package com.example.back.service.notice;


import com.example.back.domain.notice.NoticeVO;
import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticesCriteriaDTO;

import java.util.Optional;

public interface NoticeService {
//  목록과 페이징 처리
    NoticesCriteriaDTO getList(int page);
//  특정 공지사항 가져오기
    Optional<NoticeDTO> getNotice(Long id);

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
