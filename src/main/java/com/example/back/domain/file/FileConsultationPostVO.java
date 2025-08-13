package com.example.back.domain.file;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @ToString
@EqualsAndHashCode(of="id")
public class FileConsultationPostVO {
    private Long fileId;
    private Long consultationPostId;
}
