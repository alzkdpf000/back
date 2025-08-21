package com.example.back.dao.likes;

import com.example.back.dto.likes.LikesDTO;
import com.example.back.mapper.likes.LikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikesDAO {

    private final LikesMapper likesMapper;

    public void addLike(LikesDTO dto) {
        likesMapper.insert(dto);
    }

    public void removeLike(LikesDTO dto) {
        likesMapper.deleteByMemberAndDoctor(dto);
    }

    public boolean isLiked(LikesDTO dto) {
        return likesMapper.selectByMemberIdAndDoctorId(dto) > 0;
    }

    public int getLikesCount(Long doctorId) {
        return likesMapper.countByDoctorId(doctorId);
    }

}