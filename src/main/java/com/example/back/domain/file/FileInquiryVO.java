package com.example.back.domain.file;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @ToString
@EqualsAndHashCode(of="id")
public class FileInquiryVO {
    private Long fileId;
    private Long inquiryId;
}
