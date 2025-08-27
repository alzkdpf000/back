package com.example.back.dto.memberfile;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@EqualsAndHashCode(of = "memberId")
@NoArgsConstructor
public class MemberFileDTO {
    private Long fileId;
    private Long memberId;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String kakaoProfileUrl;
    private String provider;
    private String fileOriginalName;
    private String createdDatetime;
    private String updatedDatetime;
}
