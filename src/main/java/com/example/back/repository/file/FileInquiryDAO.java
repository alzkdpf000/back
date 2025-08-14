package com.example.back.repository.file;

import com.example.back.dto.file.FileDTO;
import com.example.back.mapper.file.FileConsultationPostMapper;
import com.example.back.mapper.file.FileInquiryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FileInquiryDAO {
    private final FileInquiryMapper fileInquiryMapper;

    // 상담글 번호로 해당 상담글 이미지파일 목록 조회
    public List<FileDTO> findFilesByInquiryId(Long inquiryId) {
        return fileInquiryMapper.selectFilesByInquiryId(inquiryId);
    }
}
