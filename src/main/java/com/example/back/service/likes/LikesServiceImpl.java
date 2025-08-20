package com.example.back.service.likes;

import com.example.back.dto.likes.LikesDTO;
import com.example.back.mapper.likes.LikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesMapper likesMapper;

    @Override
    public void addLike(LikesDTO dto) {
        likesMapper.insert(dto);
    }

    @Override
    public void removeLike(LikesDTO dto) {
        likesMapper.deleteByMemberAndDoctor(dto);
    }

    @Override
    public boolean isLiked(LikesDTO dto) {
        return likesMapper.selectByMemberIdAndDoctorId(dto) > 0;
    }

    @Override
    public int getLikesCount(Long doctorId) {
        return likesMapper.countByDoctorId(doctorId);
    }
}