package com.example.back.repository.counselreply;

import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.mapper.counselreply.CounselReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CounselReplyDAO {
    private final CounselReplyMapper counselReplyMapper;

    public List<CounselReplyDTO> findTop3ConsultationPostsWithReplies(Long doctorId){
        return counselReplyMapper.selectTop3ConsultationPostsWithReplies(doctorId);
    }

}
