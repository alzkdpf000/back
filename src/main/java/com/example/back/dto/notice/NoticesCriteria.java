package com.example.back.dto.notice;

import com.example.back.util.Criteria;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class NoticesCriteria {
    private List<NoticeSummaryDTO>  noticeSummaryDTOS;
    private Criteria criteria;
}
