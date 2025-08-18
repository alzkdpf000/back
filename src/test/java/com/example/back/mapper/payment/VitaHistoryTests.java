package com.example.back.mapper.payment;

import com.example.back.common.enumeration.Result;
import com.example.back.common.enumeration.Type;
import com.example.back.dto.payment.VitaHistoryDTO;
import com.example.back.dto.payment.VitaHistoryTypeDTO;
import com.example.back.repository.payment.VitaHistoryDAO;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class VitaHistoryTests {
    @Autowired
    private VitaHistoryMapper vitaHistoryMapper;
    @Autowired
    private VitaHistoryDAO vitaHistoryDAO;

    @Test
    public void testSearchVitaHistories(){
        Criteria criteria = new Criteria(1,10);
        Search search = new Search();
        List<VitaHistoryTypeDTO>  types = new ArrayList<>();
        search.setKeyword("test");
        VitaHistoryTypeDTO vitaHistoryTypeDTO = new VitaHistoryTypeDTO();
        vitaHistoryTypeDTO.setResult(Result.DONE);
        vitaHistoryTypeDTO.setType(Type.CHARGE);
        types.add(vitaHistoryTypeDTO);
        search.setTypes(types);

        List<VitaHistoryDTO> vitaHistoryDTOS = vitaHistoryMapper.searchVitaHistories(criteria, search);
        log.info("vitaHistoryDTOS:{}",vitaHistoryDTOS);
    }
    @Test
    public void testSearchCountVitaHistory(){
        Search search = new Search();
        List<VitaHistoryTypeDTO>  types = new ArrayList<>();
        search.setKeyword("test");
        VitaHistoryTypeDTO vitaHistoryTypeDTO = new VitaHistoryTypeDTO();
        vitaHistoryTypeDTO.setResult(Result.DONE);
        vitaHistoryTypeDTO.setType(Type.CHARGE);
        types.add(vitaHistoryTypeDTO);
        search.setTypes(types);
        int i = vitaHistoryMapper.searchCountVitaHistory(search);
        log.info("i:{}",i);
    }


    @Test
    public void testFindVitaHistories(){
        Criteria criteria = new Criteria(1,10);
        Search search = new Search();
        List<VitaHistoryTypeDTO>  types = new ArrayList<>();
        search.setKeyword("test");
        VitaHistoryTypeDTO vitaHistoryTypeDTO = new VitaHistoryTypeDTO();
        vitaHistoryTypeDTO.setResult(Result.CANCEL);
        vitaHistoryTypeDTO.setType(Type.CHARGE);
        types.add(vitaHistoryTypeDTO);
        search.setTypes(types);
        List<VitaHistoryDTO> vitaHistories =
                vitaHistoryDAO.findVitaHistories(criteria, search);
        log.info("vitaHistories:{}",vitaHistories);
    }
}
