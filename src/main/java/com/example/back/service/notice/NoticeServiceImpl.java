package com.example.back.service.notice;

import com.example.back.dto.notice.NoticeDTO;
import com.example.back.dto.notice.NoticeSummaryDTO;
import com.example.back.dto.notice.NoticesCriteriaDTO;
import com.example.back.repository.file.FileNoticeDAO;
import com.example.back.repository.notice.NoticeDAO;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;
    private final FileNoticeDAO fileNoticeDAO;
    @Override
    public NoticesCriteriaDTO getList(int page) {
        NoticesCriteriaDTO noticesCriteriaDTO = new NoticesCriteriaDTO();

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
        noticesCriteriaDTO.setCriteria(criteria);
        noticesCriteriaDTO.setNoticeSummaryDTOS(noticeSummaryDTOS);
        return noticesCriteriaDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<NoticeDTO> getNotice(Long id) {
        noticeDAO.updateNoticeReadCount(id);
        Optional<NoticeDTO> notice = noticeDAO.findById(id);
        notice.ifPresent(noticeDTO -> {
            noticeDTO.setFiles(fileNoticeDAO.findFilesByNoticeId(id));
        });
        return notice;
    }
}
