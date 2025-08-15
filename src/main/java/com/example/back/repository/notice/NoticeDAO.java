package com.example.back.repository.notice;

import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.mapper.notice.NoticeMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;
// 페이지 당 목록 조회
    public List<NoticeSummaryDTO> findAll(Criteria criteria) {
        return noticeMapper.selectAll(criteria);
    }
    public int findCountAll(){
        return noticeMapper.selectCountAll();
    }
}
