package com.example.back.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScrollCriteria {
    private int rowCount;
    private int limit;
    private String query;
    private String answerStatus;
    private int offset;

    public ScrollCriteria(int page,String query,String answerStatus) {
        rowCount = 8;
        offset = (page-1)* rowCount;
        this.query = query;
        this.answerStatus = answerStatus;

    }
}
