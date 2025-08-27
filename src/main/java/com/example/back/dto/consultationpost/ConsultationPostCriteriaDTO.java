package com.example.back.dto.consultationpost;

import com.example.back.util.Criteria;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter @ToString
public class ConsultationPostCriteriaDTO {
    List<ConsultationPostCategoryFileUserDTO>  consultationPosts;
    ScrollCriteria scrollCriteria;
    private Search search;
    private Criteria criteria;
    private int total;
}
