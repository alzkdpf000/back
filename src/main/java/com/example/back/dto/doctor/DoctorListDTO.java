package com.example.back.dto.doctor;



import com.example.back.common.enumeration.Status;
import com.example.back.dto.likes.LikesDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class DoctorListDTO {
    private Long id;
    private Long memberId;
    private String memberName;
    private String doctorLicenseNumber;
    private String doctorSpecialty;
    private Status doctorStatus;
    private String createdDatetime;
    private String updatedDatetime;
//  의사 기본정보
    private String memberKakaoProfileUrl;
//  의사 이미지
    private Long hospitalId;
    private String hospitalName;
    private String hospitalPhone;
    private String roadAddress;
    private String detailAddress;
//  + 병원 정보
    private List<LikesDTO> likes;
    private Integer likesCount;  // 좋아요 수
    private Boolean liked;
//  관심 정보
    private String[] categoryNames;
//  검색용 카테고리

    public String getHospitalAddress() {
        String road = roadAddress != null ? roadAddress : "";
        String detail = detailAddress != null ? detailAddress : "";
        return (road + " " + detail).trim();
    }
}