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
    private int offset;
    private int count;
    private int page;
    public ScrollCriteria(int page) {
        rowCount = 7;
        this.page = page;
        count = rowCount + 1;
        offset = (page - 1) * rowCount;
    }
}
