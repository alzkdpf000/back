package com.example.back.domain.review;

import com.example.back.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter @ToString(callSuper = true)
@SuperBuilder
@EqualsAndHashCode(of="id")
public class ReviewVO {
    private Long id;
    private int rating;
    private String content;
    private Status status;
    private Long memberId;
    private Long doctorId;
}
