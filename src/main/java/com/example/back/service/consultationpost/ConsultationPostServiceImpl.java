package com.example.back.service.consultationpost;

import com.example.back.dto.consultationpost.ConsultationPostCategoryFileUserDTO;
import com.example.back.dto.consultationpost.ConsultationPostCriteriaDTO;
import com.example.back.dto.file.FileDTO;
import com.example.back.repository.category.CategoryDAO;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.repository.file.FileConsultationPostDAO;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import com.example.back.util.ScrollCriteria;
import com.example.back.util.Search;
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
    public ConsultationPostCriteriaDTO get5PostsByViews(int page) {
        ConsultationPostCriteriaDTO criteria = new ConsultationPostCriteriaDTO();
        ScrollCriteria scrollCriteria = new ScrollCriteria(page);
        List<ConsultationPostCategoryFileUserDTO> consultationPostDAO5OrderByViewCountDesc = consultationPostDAO.find5OrderByViewCountDesc(scrollCriteria);
        boolean hasMore = consultationPostDAO5OrderByViewCountDesc.size() > scrollCriteria.getRowCount();
        if (hasMore) {
            consultationPostDAO5OrderByViewCountDesc.remove(consultationPostDAO5OrderByViewCountDesc.size() - 1);
        }
        consultationPostDAO5OrderByViewCountDesc.forEach((post) -> {
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDatetime()));
            Long consultationPostId = post.getId();
            log.info("{}", consultationPostId);
            List<String> categories = categoryDAO.findCategoryByPostId(consultationPostId);
            log.info("{}", categories);
            post.setCategories(categories);
            List<FileDTO> files = fileConsultationPostDAO.findFilesByPostId(consultationPostId);
            post.setConsultationPostFiles(files);
        });
        criteria.setConsultationPosts(consultationPostDAO5OrderByViewCountDesc);
        criteria.setScrollCriteria(scrollCriteria);
        return criteria;
    }

    @Override
    public ConsultationPostCriteriaDTO getPostList(int page) {
        // 검색조건 없는 경우, 빈 Search 객체 넘김
        return getPostList(page, new Search());
    }

    @Override
    public ConsultationPostCriteriaDTO getPostList(int page, Search search) {
        // null 방어
        if (search.getCategories() == null) {
            search.setCategories(new String[0]);
        }
        if (search.getKeyword() == null) {
            search.setKeyword("");
        }

        ConsultationPostCriteriaDTO dto = new ConsultationPostCriteriaDTO();

        int totalCount = consultationPostDAO.findCountPostList(search);
        Criteria criteria = new Criteria(page, totalCount);

        List<ConsultationPostCategoryFileUserDTO> consultationPosts = consultationPostDAO.findPostList(criteria, search);

        boolean hasMore = consultationPosts.size() > criteria.getRowCount();
        criteria.setHasMore(hasMore);

        if (hasMore) {
            consultationPosts.remove(consultationPosts.size() - 1);
        }

        dto.setConsultationPosts(consultationPosts);
        dto.setCriteria(criteria);
        dto.setTotal(totalCount);
        dto.setSearch(search);

        return dto;
    }
}
