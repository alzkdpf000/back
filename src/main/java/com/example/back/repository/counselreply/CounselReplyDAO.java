package com.example.back.repository.counselreply;

import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.mapper.counselreply.CounselReplyMapper;
import com.example.back.util.Criteria;
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

//  해당 의사의 댓글 전체 조회(게시글 제목 포함)
public List<CounselReplyDTO> findRepliesWithPostTitleByDoctorId(Long doctorId, Criteria criteria) {
    return counselReplyMapper.selectRepliesWithPostTitleByDoctorId(doctorId, criteria);
}

    /* 해당 의사의 댓글 총 개수 조회*/
    public int countRepliesByDoctorId(Long doctorId) {
        return counselReplyMapper.countRepliesByDoctorId(doctorId);
    }

}
