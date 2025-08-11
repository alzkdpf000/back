package com.example.back.mapper.file;

import com.example.back.dto.file.FileConsultationPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileConsultationPostMapper {
    // 상담글 번호로 해당 상담글 이미지파일 목록 조회
    List<FileConsultationPostDTO> selectFilesByPostId(Long consultationPostId);
}
