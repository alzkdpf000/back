package com.example.back.service.houseCall;

public interface HouseCallService {

    // 회원이 특정 의사에게 방문진료 완료했는지 확인
    public boolean hasVisitedDoctor(Long memberId, Long doctorId);
}
