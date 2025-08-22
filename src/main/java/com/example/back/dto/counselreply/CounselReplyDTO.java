package com.example.back.dto.counselreply;

import com.example.back.common.enumeration.Acceptance;
import com.example.back.common.enumeration.Status;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
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
    private Acceptance counselReplyAcceptance;
    private Long doctorId;
    private ConsultationPostDTO consultationPost;
    private String createdDatetime;
    private String createdDate;
    private String updatedDatetime;
}
