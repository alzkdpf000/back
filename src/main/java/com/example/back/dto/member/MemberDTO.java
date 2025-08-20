package com.example.back.dto.member;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Role;
import com.example.back.common.enumeration.Status;
import com.example.back.dto.consultationpost.ConsultationPostDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private String kakaoEmail;
    private String kakaoProfileUrl;
    private Role role;
    private String createdDate;
    private int memberVitaAmount;
    private String createdDatetime;
    private String updatedDatetime;
    private List<ConsultationPostDTO>  consultationPosts;
    private boolean remember;

}
