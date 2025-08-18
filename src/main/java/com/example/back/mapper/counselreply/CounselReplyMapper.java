package com.example.back.mapper.counselreply;

import com.example.back.dto.counselreply.CounselReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CounselReplyMapper {
//  내가 작성한 상담글 제목을 포함한 정보 상위 3개 가져오기
    public List<CounselReplyDTO> selectTop3ConsultationPostsWithReplies(Long doctorId);
}
