package com.example.back.dto.file;

import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Setter @Getter
@ToString
@EqualsAndHashCode(of="id")
public class FileConsultationPostDTO {
    private Long fileId;
    private Long consultationPostId;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private Status fileStatus;
    private String createdDate;
    private String updatedDate;
}
