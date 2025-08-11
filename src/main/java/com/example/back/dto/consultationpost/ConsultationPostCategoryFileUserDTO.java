package com.example.back.dto.consultationpost;

import com.example.back.common.enumeration.Status;
import com.example.back.dto.category.CategoryDTO;
import com.example.back.dto.file.FileConsultationPostDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
@ToString @EqualsAndHashCode(of="id")
public class ConsultationPostCategoryFileUserDTO {
    private Long id;
    private String consultationPostTitle;
    private String consultationPostContent;
    private Status consultationPostStatus;
    private String consultationPostViewCount;
    private Long memberId;
    private String createdDate;
    private String updatedDate;
    private List<FileConsultationPostDTO> postFiles;
    private List<CategoryDTO> categories;
    private String memberName;
    private String memberFilePath;
}
