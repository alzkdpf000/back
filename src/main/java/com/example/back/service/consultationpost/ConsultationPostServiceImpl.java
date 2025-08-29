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

    //    조회순(인기순)으로 QnA 가져오기
    @Override
    public ConsultationPostCriteriaDTO getPostsByViews(int page) {
        ConsultationPostCriteriaDTO criteria = new ConsultationPostCriteriaDTO();
        ScrollCriteria scrollCriteria = new ScrollCriteria(page);
        List<ConsultationPostCategoryFileUserDTO> posts = consultationPostDAO.findOrderByViewCountDesc(scrollCriteria);
        boolean hasMore = posts.size() > scrollCriteria.getRowCount();
        if (hasMore) {
            posts.remove(posts.size() - 1);
        }
        posts.forEach((post) -> {
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDatetime()));
            Long consultationPostId = post.getId();
            log.info("{}", consultationPostId);
            List<String> categories = categoryDAO.findCategoryByPostId(consultationPostId);
            log.info("{}", categories);
            post.setCategories(categories);
            List<FileDTO> files = fileConsultationPostDAO.findFilesByPostId(consultationPostId);
            post.setConsultationPostFiles(files);
        });
        criteria.setConsultationPosts(posts);
        criteria.setScrollCriteria(scrollCriteria);
        return criteria;
    }

    @Override
    public ConsultationPostCriteriaDTO getPostList(int page) {
        // 검색조건 없는 경우
        return getPostList(page, new Search(), "created_datetime DESC");
    }

    @Override
    public ConsultationPostCriteriaDTO getPostList(int page, Search search, String order) {
        // null 방어
        if (search.getCategories() == null) {
            search.setCategories(new String[0]);
        }
        if (search.getKeyword() == null) {
            search.setKeyword("");
        }

        ConsultationPostCriteriaDTO dto = new ConsultationPostCriteriaDTO();

        String orderBy = "created_datetime DESC"; // 최신순 기본
        if ("viewCount".equals(order)) {
            orderBy = "view_count DESC";
        }

        // 전체 게시글 수
        int totalCount = consultationPostDAO.findCountPostList(search);
        Criteria criteria = new Criteria(page, totalCount);

        // 게시글 리스트 조회
        List<ConsultationPostCategoryFileUserDTO> consultationPosts =
                consultationPostDAO.findPostList(criteria, search, orderBy);

        // hasMore
        boolean hasMore = consultationPosts.size() > criteria.getRowCount();
        criteria.setHasMore(hasMore);
        if (hasMore) {
            consultationPosts.remove(consultationPosts.size() - 1);
        }

        // 게시글 categories, files, relativeDate 세팅
        consultationPosts.forEach(post -> {
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDatetime()));

            Long consultationPostId = post.getId();

            // 카테고리
            List<String> categories = categoryDAO.findCategoryByPostId(consultationPostId);
            post.setCategories(categories);

            // 파일
            List<FileDTO> files = fileConsultationPostDAO.findFilesByPostId(consultationPostId);
            post.setConsultationPostFiles(files);
        });

        dto.setConsultationPosts(consultationPosts);
        dto.setCriteria(criteria);
        dto.setTotal(totalCount);
        dto.setSearch(search);

        return dto;
    }
}
