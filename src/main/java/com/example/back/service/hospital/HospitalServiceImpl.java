package com.example.back.service.hospital;

import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.domain.hospital.HospitalVO;
import com.example.back.mapper.hospital.HospitalMapper;
import com.example.back.repository.hospital.HospitalDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalMapper hospitalMapper;
    private final HospitalDAO hospitalDAO;

    @Override
    public Long register(HospitalDTO hospitalDTO) {
        hospitalDAO.save(gtoVO(hospitalDTO));
        return hospitalDTO.getId();
    }
}
