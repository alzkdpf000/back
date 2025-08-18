package com.example.back.dto.doctor;

import com.example.back.util.Criteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class DoctorCriteriaDTO {
    private List<DoctorDTO> doctorsList;
    private Criteria criteria;
    private int total;
}
