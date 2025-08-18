package com.example.back.repository.payment;

import com.example.back.dto.payment.VitaHistoryDTO;
import com.example.back.mapper.payment.VitaHistoryMapper;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VitaHistoryDAO {
    private final VitaHistoryMapper vitaHistoryMapper;


    public List<VitaHistoryDTO> findVitaHistories(Criteria criteria, Search search){
        return vitaHistoryMapper.searchVitaHistories(criteria,search);
    }
    public int findCountVitaHistory(Search search){
        return vitaHistoryMapper.searchCountVitaHistory(search);
    }
}
