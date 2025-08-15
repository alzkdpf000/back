package com.example.back.domain.notice;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter @EqualsAndHashCode(of="id")
public class NoticeVO extends Period {
    private Long id;
    private String noticesTitle;
    private String noticesContent;
    private Status noticeStatus;
    private int noticesViewCount;
    private Long memberId;

}
