package com.example.back.dto.doctor;

import com.example.back.domain.doctor.DoctorVO;
import com.example.back.dto.counselreply.CounselReplyDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter @Setter
@ToString
public class DoctorDetailDTO {
    private DoctorListDTO doctorListDTO;
    private List<CounselReplyDTO> counselReplyDTOList;
}
