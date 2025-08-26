package com.example.back.service.houseCall;

import com.example.back.mapper.HouseCall.HouseCallMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseCallServiceImpl implements HouseCallService {

    private final HouseCallMapper houseCallMapper;

    @Override
    public boolean hasVisitedDoctor(Long memberId, Long doctorId) {
        try {
            return houseCallMapper.existsHouseCallByMemberAndDoctor(memberId, doctorId) > 0;
        } catch (Exception e) {
            System.err.println("방문진료 확인 중 오류: " + e.getMessage());
            return false;
        }
    }
}