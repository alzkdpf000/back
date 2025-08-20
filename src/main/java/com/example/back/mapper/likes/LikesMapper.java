package com.example.back.mapper.likes;

import com.example.back.dto.likes.LikesDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikesMapper {

    void insert(LikesDTO dto);

    void deleteByMemberAndDoctor(LikesDTO dto);

    int selectByMemberIdAndDoctorId (LikesDTO dto);

    int countByDoctorId(Long doctorId);

}