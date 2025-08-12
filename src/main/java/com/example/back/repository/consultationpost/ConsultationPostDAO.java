package com.example.back.repository.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.mapper.consultationpost.ConsultationPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsultationPostDAO {
    private final ConsultationPostMapper consultationPostMapper;

    //    조회순(인기순)5개 게시글 조회 현재 임시로 3개만
    public List<ConsultationPostCategoryFileUserDTO> find5OrderByViewCountDesc(int offest) {
        return consultationPostMapper.select5OrderByViewCountDesc(offest);
    }

}
