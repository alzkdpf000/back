package com.example.back.mapper.counselreply;

import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CounselReplyMapper {
//  내가 작성한 상담글 제목을 포함한 정보 상위 3개 가져오기
    public List<CounselReplyDTO> selectTop3ConsultationPostsWithReplies(Long doctorId);

//  해당 의사가 작성한 답변글 전체 가져오기(상담글 제목 포함)
    List<CounselReplyDTO> selectRepliesWithPostTitleByDoctorId(
            @Param("doctorId") Long doctorId,
            @Param("criteria") Criteria criteria
    );

//  해당 의사가 작성한 답변글 전체 개수
    int countRepliesByDoctorId(@Param("doctorId") Long doctorId);
}
