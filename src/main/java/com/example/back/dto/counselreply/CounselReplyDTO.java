package com.example.back.dto.counselreply;

import com.example.back.common.enumeration.Acceptance;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class CounselReplyDTO {
    private Long id;
    private String counselReplyContent;
    private Status counselReplyStatus;
    private Acceptance counselReplyAcceptance;
    private Long doctorId;
    private String createdDatetime;
    private String updatedDatetime;

    // 게시글 정보
    private Long consultationPostId;
    private String consultationPostTitle;
    private String consultationPostStatus;

    // 카테고리 이름 리스트
    private List<String> categoryNames;

    // 화면에서 바로 보여줄 작성일
    private String createdDate;
}