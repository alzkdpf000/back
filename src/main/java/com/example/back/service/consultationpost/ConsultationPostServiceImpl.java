package com.example.back.service.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationPostServiceImpl implements ConsultationPostService {
    private final ConsultationPostDAO consultationPostDAO;


    //    조회순(인기순)5개 게시글 조회 현재 임시로 3개만
    @Override
//    @Transactional(readOnly = true)
    public List<ConsultationPostCategoryFileUserDTO> get5PostsByViews(int offest) {
        List<ConsultationPostCategoryFileUserDTO> consultationPostDAO5OrderByViewCountDesc = consultationPostDAO.find5OrderByViewCountDesc(offest);
        consultationPostDAO5OrderByViewCountDesc.forEach((post)->{
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDate()));
        });
        return consultationPostDAO5OrderByViewCountDesc;
    }
}
