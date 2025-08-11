package com.example.back.dto.category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private String createdDate;
    private String updatedDate;
}
