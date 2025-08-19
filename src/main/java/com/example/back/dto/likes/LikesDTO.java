package com.example.back.dto.likes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesDTO {
    private Long memberId;
    private Long doctorId;
}