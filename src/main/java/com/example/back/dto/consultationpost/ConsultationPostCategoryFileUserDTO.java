package com.example.back.dto.consultationpost;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Status;
import com.example.back.dto.file.FileDTO;
import com.example.back.util.ScrollCriteria;
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
    private String consultationPostAnswerCount;
    private Long memberId;
    private String createdDatetime;
    private Provider memberProvider;
    private List<FileDTO> consultationPostFiles;
    private List<String> categories;
    private String memberName;
    private String memberFilePath;
    private String relativeDate;
}
