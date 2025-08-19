package com.example.back.mapper.payment;

import com.example.back.dto.payment.VitaHistoryDTO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VitaHistoryMapper {


    //    관리자 페이지 충전 사용 내역들
    public List<VitaHistoryDTO> searchVitaHistories(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //    관리자 페이지 결제 수
    public int searchCountVitaHistory(@Param("search") Search search);
}
