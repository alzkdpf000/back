package com.example.back.service.file;

import com.example.back.domain.file.FileConsultationPostVO;
import com.example.back.dto.file.FileConsultationPostDTO;


import java.util.List;

public interface FileConsultationPostService {
    // 상담글 번호로 해당 상담글 이미지파일 목록 조회
    List<String> getFilesByPostId(Long postId);

    default FileConsultationPostVO toVO(FileConsultationPostDTO fileConsultationPostDTO) {
        return FileConsultationPostVO.builder()
                .fileId(fileConsultationPostDTO.getFileId())
                .consultationPostId(fileConsultationPostDTO.getConsultationPostId())
                .filePath(fileConsultationPostDTO.getFilePath())
                .fileStatus(fileConsultationPostDTO.getFileStatus())
                .fileName(fileConsultationPostDTO.getFileName())
                .fileSize(fileConsultationPostDTO.getFileSize())
                .createdDate(fileConsultationPostDTO.getCreatedDate())
                .updatedDate(fileConsultationPostDTO.getUpdatedDate())
                .build();
    }
}
