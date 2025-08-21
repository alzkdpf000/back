package com.example.back.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Search {
    private String[] categories;
    private String keyword;
    private int page;
}
