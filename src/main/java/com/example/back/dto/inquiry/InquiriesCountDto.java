package com.example.back.dto.inquiry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@EqualsAndHashCode(of = "id")
@Setter
@ToString
public class InquiriesCountDto {
    private String answerCount;
    private String noAnswerCount;
}
