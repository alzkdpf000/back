package com.example.back.dto.consultationpost;

import com.example.back.common.enumeration.Status;
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
    private String createdDate;
    private List<String> consultationPostFiles;
    private List<String> categories;
    private String memberName;
    private String memberFilePath;
}
