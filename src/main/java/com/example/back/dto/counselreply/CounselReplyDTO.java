package com.example.back.dto.counselreply;

import com.example.back.common.enumeration.Accpetance;
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
public class CounselReplyDTO {
    private Long id;
    private String counselReplyContent;
    private Status counselReplyStatus;
    private Accpetance counselReplyAccpetance;
    private Long doctorId;
    private Status consultationPostStatus;
    private Long consultationPostId;
    private String consultationPostTitle;
    private String createdDatetime;
    private String createdDate;
    private String updatedDatetime;
}
