package com.example.back.repository.file;

import com.example.back.dto.file.FileDTO;
import com.example.back.mapper.file.FileNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileNoticeDAO {
    private final FileNoticeMapper fileNoticeMapper;

    public List<FileDTO> findFilesByNoticeId(Long noticeId){
        return fileNoticeMapper.selectFilesByNoticeId(noticeId);
    }
}
