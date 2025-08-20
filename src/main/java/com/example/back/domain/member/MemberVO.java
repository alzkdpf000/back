package com.example.back.domain.member;

import com.example.back.audit.Period;
import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Role;
import com.example.back.common.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper=true)
@SuperBuilder
@EqualsAndHashCode(of = "id")

public class MemberVO extends Period {

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

}
