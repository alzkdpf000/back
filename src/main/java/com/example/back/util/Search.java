package com.example.back.util;

import com.example.back.dto.payment.VitaHistoryTypeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Search {
    private String[] categories;
    private String keyword;
    private int page;
    private List<VitaHistoryTypeDTO> types;
}
