package com.example.back.dto.review;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class ReviewDTO {
    private Long id;
    private int rating;
    private String content;
    private String status;
    private Long memberId;
    private Long doctorId;
    private String createdDatetime;
    private String createdDate;
    private String updatedDatetime;
    private String updatedDate;
}
