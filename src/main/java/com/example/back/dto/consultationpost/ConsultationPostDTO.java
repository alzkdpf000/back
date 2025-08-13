package com.example.back.dto.consultationpost;

import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class ConsultationPostDTO {
    private Long id;
    private String consultationPostTitle;
    private String consultationPostContent;
    private Status consultationPostStatus;
    private String consultationPostViewCount;
    private String consultationPostAnswerCount;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;
}
