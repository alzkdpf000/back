package com.example.back.dto.member;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Status;
import com.example.back.util.Criteria;
import com.example.back.util.Search;
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
@EqualsAndHashCode(of = "id")
public class MemberCriteriaDTO {
    private List<MemberDTO> members;
    private Criteria criteria;
    private Search search;
    private int total;
}
