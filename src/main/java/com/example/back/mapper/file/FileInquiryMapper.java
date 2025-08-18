package com.example.back.mapper.file;


import com.example.back.dto.file.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileInquiryMapper {
    // 상담글 번호로 해당 상담글 이미지파일 목록 조회
    public List<FileDTO> selectFilesByInquiryId(Long inquiryId);
}
