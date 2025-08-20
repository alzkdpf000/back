package com.example.back.service.vitahistory;

import com.example.back.repository.vitahistory.VitaHistoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VitaHistoryServiceImpl implements VitaHistoryService {
    private final VitaHistoryDAO vitaHistoryDAO;

}
