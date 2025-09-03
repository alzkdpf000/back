package com.example.back.service.hospital;

import com.example.back.dto.hospital.HospitalDTO;
import com.example.back.domain.hospital.HospitalVO;

public interface HospitalService {
    public Long register(HospitalDTO hospitalDTO);


    default HospitalVO gtoVO(HospitalDTO hospitalDTO){
        return HospitalVO.builder()
                .hospitalId(hospitalDTO.getId())
                .hospitalDetailAddress(hospitalDTO.getDetailAddress())
                .hospitalPhone(hospitalDTO.getHospitalPhone())
                .hospitalName(hospitalDTO.getHospitalName())
                .hospitalRoadAddress(hospitalDTO.getRoadAddress())
                .hospitalDetailAddress(hospitalDTO.getDetailAddress())
                .hospitalHomePageUrl(hospitalDTO.getHospitalHomePageUrl())
                .build();
    }
}
