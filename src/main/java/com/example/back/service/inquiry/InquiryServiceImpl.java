package com.example.back.service.inquiry;

import com.example.back.dto.file.FileDTO;
import com.example.back.dto.inquiry.InquiriesCountDto;
import com.example.back.dto.inquiry.InquiryMemberReplyDTO;
import com.example.back.dto.inquiry.InquirySummaryDTO;
import com.example.back.repository.file.FileInquiryDAO;
import com.example.back.repository.inquiry.InquiryDAO;
import com.example.back.util.DateUtils;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryDAO inquiryDAO;
    private final FileInquiryDAO fileInquiryDAO;


    //    문의글 목록 및 통계
    @Override
    public InquirySummaryDTO getInquiryListWithAnswerStats(Search search) {
        ScrollCriteria scrollCriteria = new ScrollCriteria(search.getPage());
        InquirySummaryDTO inquirySummaryDTO = new InquirySummaryDTO();
        List<InquiryMemberReplyDTO> inquiriesByEmailOrId = inquiryDAO.findInquiriesByEmailOrId(scrollCriteria,search);
        if(inquiriesByEmailOrId.size() > scrollCriteria.getRowCount()){
            inquiriesByEmailOrId.remove(inquiriesByEmailOrId.size()-1);
        }
        inquiriesByEmailOrId.forEach(inquiryMemberReplyDTO -> {
            inquiryMemberReplyDTO.setCreatedDateTimeInquiry(DateUtils.getCreatedDate(inquiryMemberReplyDTO.getCreatedDateTime()));
            if(inquiryMemberReplyDTO.getAnswerDatetime() != null){
                inquiryMemberReplyDTO.setAnswerDatetimeReply(DateUtils.getCreatedDate(inquiryMemberReplyDTO.getAnswerDatetime()));
            }
        });

        InquiriesCountDto answerCounts = inquiryDAO.getAnswerCounts(search);
        inquirySummaryDTO.setScrollCriteria(scrollCriteria);
        inquirySummaryDTO.setInquiriesCountDto(answerCounts);
        inquirySummaryDTO.setInquiryMemberReplyDTOs(inquiriesByEmailOrId);
        inquirySummaryDTO.setSearch(search);
        return inquirySummaryDTO;
    }


    //  문의글 상세보기
    @Transactional(rollbackFor = Exception.class)
    public Optional<InquiryMemberReplyDTO> getInquiryDetail(Long inquiryId) {
        inquiryDAO.updateInquiryReadCount(inquiryId);
        Optional<InquiryMemberReplyDTO> activeInquiryWithReplyById = inquiryDAO.findActiveInquiryWithReplyById(inquiryId);
        List<FileDTO> filesByInquiryId = fileInquiryDAO.findFilesByInquiryId(inquiryId);

        activeInquiryWithReplyById.ifPresent(inquiryMemberReplyDTO -> {
            inquiryMemberReplyDTO.setCreatedDateTimeInquiry(DateUtils.getCreatedDate(inquiryMemberReplyDTO.getCreatedDateTime()));
            inquiryMemberReplyDTO.setFiles(filesByInquiryId);
        });
        return activeInquiryWithReplyById;
    }
}
