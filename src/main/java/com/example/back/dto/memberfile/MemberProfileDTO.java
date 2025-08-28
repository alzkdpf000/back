package com.example.back.dto.memberfile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class MemberProfileDTO {

        private Long memberId;
        private String memberName;
        private String memberEmail;
        private String memberPhone;
        private String provider;
        private String kakaoEmail;
        private String kakaoProfileUrl;
        private Long fileId;
        private String fileName;
        private String filePath;
        private Long profileFileId;


}
