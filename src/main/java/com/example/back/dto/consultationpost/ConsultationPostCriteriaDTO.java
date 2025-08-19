package com.example.back.dto.consultationpost;

import com.example.back.util.ScrollCriteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter @ToString
public class ConsultationPostCriteriaDTO {
    List<ConsultationPostCategoryFileUserDTO>  consultationPosts;
    ScrollCriteria scrollCriteria;

}
