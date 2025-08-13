package com.example.back.dto.doctor;


import ch.qos.logback.core.status.Status;
import com.example.back.dto.likes.LikesDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter @ToString(callSuper = true)
@Setter
@SuperBuilder
@EqualsAndHashCode(of="id")
public class DoctorListDTO {
    private Long id;
    private String memberName;
    private String doctorSpecialty;
    private Status doctorStatus;
    private Long memberId;
    private String createdDate;
    private String updatedDate;
//  의사 기본정보
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalAddress;
//  + 병원 정보
    private List<LikesDTO> likes;
//  관심 정보
}