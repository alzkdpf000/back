package com.example.back.domain.counselreply;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Acceptance;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter @EqualsAndHashCode(of = "id")
public class CounselReplyVO extends Period {
    private Long id;
    private String counselReplyContent;
    private Status counselReplyStatus;
    private Acceptance counselReplyAcceptance;
    private Long doctorId;
    private Long consultationPostId;

}

//id                       bigint unsigned auto_increment primary key,
//counsel_reply_content    text not null,
//counsel_reply_status     enum ('active','inactive')     default 'active',
//counsel_reply_acceptance enum ('accepted','unaccepted') default 'unaccepted',
//doctor_id                bigint unsigned,
//consultation_post_id     bigint unsigned,
//created_date             datetime                       default current_timestamp,
//updated_date             datetime                       default current_timestamp,
