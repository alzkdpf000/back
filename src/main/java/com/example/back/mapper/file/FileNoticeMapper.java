package com.example.back.mapper.file;

import com.example.back.dto.file.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileNoticeMapper {
    public List<FileDTO> selectFilesByNoticeId(Long noticeId);
}
