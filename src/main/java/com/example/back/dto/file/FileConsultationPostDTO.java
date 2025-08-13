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
public class FileConsultationPostDTO {
    private Long fileId;
    private Long consultationPostId;
}
