package com.example.back.service.file;

import com.example.back.dto.file.FileConsultationPostDTO;
import com.example.back.mapper.file.FileConsultationPostMapper;
import com.example.back.repository.file.FileConsultationPostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileConsultationPostServiceImpl implements FileConsultationPostService {
    private final FileConsultationPostDAO fileConsultationPostDAO;

    @Override
    @Transactional(readOnly = true)
    // 상담글 번호로 해당 상담글 이미지파일 목록 조회
    public List<FileConsultationPostDTO> getFilesByPostId(Long consultationPostId) {
        return fileConsultationPostDAO.findFilesByPostId(consultationPostId);
    }
}
