package com.example.back.service.notice;

import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.dto.notice.NoticesCriteria;
import com.example.back.mapper.notice.NoticeMapper;
import com.example.back.repository.notice.NoticeDAO;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;

    @Override
    public NoticesCriteria getList(int page) {
        NoticesCriteria noticesCriteria = new NoticesCriteria();

        Criteria criteria = new Criteria(page,noticeDAO.findCountAll());
        List<NoticeSummaryDTO> noticeSummaryDTOS = noticeDAO.findAll(criteria);
        log.info(criteria.toString());
        noticeSummaryDTOS.forEach(noticeSummaryDTO -> {
            noticeSummaryDTO.setCreatedDateAndDay(DateUtils.getMonthAndDay(noticeSummaryDTO.getCreatedDatetime()));
        });

        criteria.setHasMore(noticeSummaryDTOS.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            noticeSummaryDTOS.remove(noticeSummaryDTOS.size() - 1);
        }
        noticesCriteria.setCriteria(criteria);
        noticesCriteria.setNoticeSummaryDTOS(noticeSummaryDTOS);
        return noticesCriteria;
    }
}
