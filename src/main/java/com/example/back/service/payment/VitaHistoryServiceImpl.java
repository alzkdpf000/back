package com.example.back.service.payment;

import com.example.back.dto.payment.VitaHistoryCriteriaDTO;
import com.example.back.dto.payment.VitaHistoryDTO;
import com.example.back.repository.payment.VitaHistoryDAO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VitaHistoryServiceImpl implements VitaHistoryService {
    private final VitaHistoryDAO vitaHistoryDAO;


    @Override
    public VitaHistoryCriteriaDTO getVitaHistories(Search search) {
        VitaHistoryCriteriaDTO vitaHistoryCriteriaDTO = new VitaHistoryCriteriaDTO();
        int total = vitaHistoryDAO.findCountVitaHistory(search);
        Criteria criteria = new Criteria(search.getPage(), total);
        List<VitaHistoryDTO> vitaHistories = vitaHistoryDAO.findVitaHistories(criteria, search);
        vitaHistoryCriteriaDTO.setVitaHistories(vitaHistories);
        vitaHistoryCriteriaDTO.setTotal(total);
        vitaHistoryCriteriaDTO.setCriteria(criteria);
        vitaHistoryCriteriaDTO.setSearch(search);
        return vitaHistoryCriteriaDTO;
    }
}
