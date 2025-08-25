package com.example.back.mapper.HouseCall;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseCallMapper {

    int existsHouseCallByMemberAndDoctor(@Param("memberId") Long memberId,
                                         @Param("doctorId") Long doctorId);
}