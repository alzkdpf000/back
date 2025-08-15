package com.example.back.dto.notice;

import com.example.back.common.enumeration.Status;
import com.example.back.dto.file.FileDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter @Setter
@EqualsAndHashCode(of="id")
public class NoticeDTO {
    private Long id;
    private String noticesTitle;
    private String noticesContent;
    private Status noticeStatus;
    private int noticesViewCount;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;
    private List<FileDTO> files;
}
