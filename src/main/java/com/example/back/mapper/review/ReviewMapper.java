package com.example.back.mapper.review;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.dto.review.ReviewDTO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

//  해당 의사의 리뷰 목록 조회
    public List<ReviewDTO> selectReviewsByDoctorId(Long doctorId, Criteria criteria);

//  해당 의사의 리뷰 총 개수 조회(페이징처리용)
    public int countReviewsByDoctorId(Long doctorId);

//  현재 로그인 한 회원이 해당 의사에게 방문진료를 받은 적이 있는지 체크(리뷰 작성 유효성검사)
    public int existsHouseCallByMemberAndDoctor
            (@Param("memberId") Long memberId,
             @Param("doctorId") Long doctorId);

}
