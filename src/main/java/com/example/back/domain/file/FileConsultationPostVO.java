package com.example.back.domain.file;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@Getter @SuperBuilder
public class FileConsultationPostVO extends Period {
    private Long fileId;
    private Long consultationPostId;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private Status fileStatus;
}
