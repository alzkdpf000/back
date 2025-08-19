package com.example.back.dto.likes;

import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @EqualsAndHashCode(of = "id")
@Setter @ToString
public class LikesDTO {
    private Long id;
    private Long memberId;
    private Long doctorId;
    private String createdDatetime;
    private String updatedDatetime;
}
