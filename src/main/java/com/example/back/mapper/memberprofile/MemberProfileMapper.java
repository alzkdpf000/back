package com.example.back.mapper.memberprofile;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberProfileMapper {

//    특정 파일 ID를 참조하는 모든 파일 삭제
    public void deleteByFileId(@Param("fileId") Long fileId);

}
