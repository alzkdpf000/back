package com.example.back.service.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostCriteria;
import com.example.back.dto.file.FileDTO;
import com.example.back.repository.category.CategoryDAO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.repository.file.FileConsultationPostDAO;
import com.example.back.util.DateUtils;
import com.example.back.util.ScrollCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultationPostServiceImpl implements ConsultationPostService {
    private final ConsultationPostDAO consultationPostDAO;
    private final CategoryDAO categoryDAO;
    private final FileConsultationPostDAO fileConsultationPostDAO;

    //    조회순(인기순)5개 게시글 조회 현재 임시로 3개만
    @Override
//    @Transactional(readOnly = true)
    public ConsultationPostCriteria get5PostsByViews(int page) {
        ConsultationPostCriteria criteria = new ConsultationPostCriteria();
        ScrollCriteria scrollCriteria = new ScrollCriteria(page);
        List<ConsultationPostCategoryFileUserDTO> consultationPostDAO5OrderByViewCountDesc = consultationPostDAO.find5OrderByViewCountDesc(scrollCriteria);

        consultationPostDAO5OrderByViewCountDesc.forEach((post) -> {
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDatetime()));
            Long consultationPostId = post.getId();
            log.info("{}",consultationPostId);
            List<String> categories = categoryDAO.findCategoryByPostId(consultationPostId);
            log.info("{}",categories);
            post.setCategories(categories);
            List<FileDTO> files = fileConsultationPostDAO.findFilesByPostId(consultationPostId);
            post.setConsultationPostFiles(files);
        });

        criteria.setConsultationPosts(consultationPostDAO5OrderByViewCountDesc);
        criteria.setScrollCriteria(scrollCriteria);
        return criteria;
    }
}
