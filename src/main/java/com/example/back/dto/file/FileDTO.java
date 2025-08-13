package com.example.back.dto.file;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class FileDTO {
    private Long id;
    private String fileOriginalName;
    private String filePath;
    private String fileName;
    private String fileSize;
    private String fileContentType;
    private String createdDatetime;
    private String updatedDatetime;
}


