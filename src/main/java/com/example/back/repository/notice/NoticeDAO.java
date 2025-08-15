package com.example.back.repository.notice;

import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.mapper.notice.NoticeMapper;
import com.example.back.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;
// 페이지 당 목록 조회
    public List<NoticeSummaryDTO> findAll(Criteria criteria) {
        return noticeMapper.selectAll(criteria);
    }
//  전체 개수 조회
    public int findCountAll(){
        return noticeMapper.selectCountAll();

    }
    //  특정 공지사항 조회
    public Optional<NoticeDTO> findById(Long id){
        return noticeMapper.selectById(id);
    }

    // 조회 수 증가
    public void updateNoticeReadCount(Long id){
        noticeMapper.updateNoticeReadCount(id);
    }

}
