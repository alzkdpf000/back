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
    private int count;

    public ScrollCriteria(int page) {
        rowCount = 7;
        count = rowCount + 1;
        offset = (page - 1) * rowCount;
    }

    public ScrollCriteria(int page, String query, String answerStatus) {
        rowCount = 7;
        offset = (page - 1) * rowCount;
        count = rowCount + 1;
        this.query = query;
        this.answerStatus = answerStatus;

    }
}
