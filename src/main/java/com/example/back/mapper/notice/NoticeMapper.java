package com.example.back.mapper.notice;

import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeMapper {
//  페이지 당 목록 조회
    public List<NoticeSummaryDTO> selectAll(Criteria criteria);
//  전체 개수 조회
    public int selectCountAll();
    //  특정 공지사항 조회
    public Optional<NoticeDTO> selectById(Long id);
    // 조회 수 증가
    public void updateNoticeReadCount(Long id);
}
