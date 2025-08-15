package com.example.back.dto.notice;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class NoticeSummaryDTO {
    private Long id;
    private String noticesTitle;
    private String createdDatetime;
    private String createdDateAndDay;
}
