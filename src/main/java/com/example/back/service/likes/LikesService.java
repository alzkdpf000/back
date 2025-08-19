package com.example.back.service.likes;

import com.example.back.dto.likes.LikesDTO;

public interface LikesService {

    void addLike(LikesDTO dto);

    void removeLike(LikesDTO dto);

    boolean isLiked(LikesDTO dto);

    int getLikesCount(Long doctorId);

}