package com.example.back.domain.file;

import com.example.back.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @ToString
@EqualsAndHashCode(of = "id")
public class FileVO extends Period {
    private Long id;
    private String fileOriginalName;
    private String filePath;
    private String fileName;
    private String fileSize;
    private String fileContentType;
}
