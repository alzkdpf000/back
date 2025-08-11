package com.example.back.service.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationPostServiceImpl implements ConsultationPostService {
    private final ConsultationPostDAO consultationPostDAO;


    //    조회순(인기순)5개 게시글 조회 현재 임시로 3개만
    @Override
    @Transactional(readOnly = true)
    public List<ConsultationPostCategoryFileUserDTO> getTop5PostsByViews() {
        return consultationPostDAO.findTop5OrderByViewCountDesc();
    }
}
