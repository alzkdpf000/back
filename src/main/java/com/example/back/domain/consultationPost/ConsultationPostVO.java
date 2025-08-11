package com.example.back.domain.consultationPost;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Time;

@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@Getter
public class ConsultationPostVO extends Period {
    private Long id;
    private String consultationPostTitle;
    private String consultationPostContent;
    private Status consultationPostStatus;
    private String consultationPostViewCount;
    private Long memberId;
}