package com.example.back.dto.member;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Role;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@EqualsAndHashCode(of = "id")
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private Status memberStatus;
    private Provider provider;
    private String KakaoEmail;
    private String KakaoProfileUrl;
    private Role role;
    private int memberVitaAmount;
    private String createdDatetime;
    private String updatedDatetime;

}
