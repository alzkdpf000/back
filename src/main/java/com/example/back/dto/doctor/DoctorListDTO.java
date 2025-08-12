package com.example.back.dto.doctor;



import com.example.back.common.enumeration.Status;
import com.example.back.dto.likes.LikesDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter @ToString(callSuper = true)
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of="id")
public class DoctorListDTO {
    private Long id;
    private String memberName;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private Long memberId;
    private Long hospitalId;
    private String createdDate;
    private String updatedDate;
//  의사 기본정보
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalAddress;
//  + 병원 정보
    private List<LikesDTO> likes;
//  관심 정보
//  상담글 정보
}