package com.example.back.service.doctor;

import com.example.back.common.enumeration.Role;
import com.example.back.common.enumeration.Status;
import com.example.back.domain.doctor.DoctorVO;
import com.example.back.domain.hospital.HospitalDTO;
import com.example.back.domain.hospital.HospitalVO;
import com.example.back.domain.member.MemberVO;
import com.example.back.dto.counselreply.CounselReplyDTO;
import com.example.back.dto.doctor.*;
import com.example.back.dto.likes.LikesDTO;
import com.example.back.dto.member.MemberDTO;
import com.example.back.mapper.counselreply.CounselReplyMapper;
import com.example.back.repository.consultationpost.ConsultationPostDAO;
import com.example.back.repository.counselreply.CounselReplyDAO;
import com.example.back.repository.doctor.DoctorDAO;
import com.example.back.service.hospital.HospitalService;
import com.example.back.service.likes.LikesService;
import com.example.back.service.member.MemberService;
import com.example.back.util.Criteria;
import com.example.back.util.DateUtils;
import com.example.back.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO;
    private final CounselReplyDAO counselReplyDAO;
    private final MemberService memberService;
    private final HospitalService hospitalService;
    private final LikesService likesService;
    private final com.example.back.dao.likes.LikesDAO likesDAO;
    private final ConsultationPostDAO consultationPostDAO;

    @Override
    public DoctorListCriteriaDTO getList(int page, Search search) {
        DoctorListCriteriaDTO doctorListCriteriaDTO = new DoctorListCriteriaDTO();

        int totalCount = doctorDAO.findCountDoctorList(search);
        Criteria criteria = new Criteria(page, totalCount);
        criteria.setCurrentMemberId(1L); // 현재 로그인 멤버 ID

        List<DoctorListDTO> doctorsList = doctorDAO.findDoctorList(criteria, search);

        doctorsList.forEach(doctor -> {
            // 좋아요 수
            doctor.setLikesCount(likesDAO.getLikesCount(doctor.getId()));
        });

        criteria.setHasMore(doctorsList.size() > criteria.getRowCount());
        if (criteria.isHasMore()) {
            doctorsList.remove(doctorsList.size() - 1);
        }

        doctorListCriteriaDTO.setDoctorsList(doctorsList);
        doctorListCriteriaDTO.setCriteria(criteria);

        return doctorListCriteriaDTO;
    }

    @Override
    public DoctorDetailCriteriaDTO getDoctorDetail(Long doctorId, int page) {
        DoctorDetailCriteriaDTO detailCriteriaDTO = new DoctorDetailCriteriaDTO();

        // 의사 정보 상세보기
        DoctorListDTO doctor = doctorDAO.findDoctorDetailById(doctorId);

        // 로그인 유저 ID (임시로 1L 주입)
        Long currentMemberId = 1L;

        // 좋아요 수 조회
        doctor.setLikesCount(likesDAO.getLikesCount(doctor.getId()));

        // 로그인 유저가 좋아요했는지 여부
        LikesDTO likesDTO = new LikesDTO(currentMemberId, doctor.getId());
        doctor.setLiked(likesService.isLiked(likesDTO));

        // 답변글 총 개수 조회
        int totalReplies = counselReplyDAO.countRepliesByDoctorId(doctorId);

        // 페이징 처리
        Criteria criteria = new Criteria(page, totalReplies);
        criteria.setCurrentMemberId(currentMemberId);

        // 답변글 + 게시글 조회
        List<CounselReplyDTO> replies = counselReplyDAO.findRepliesWithPostTitleByDoctorId(doctorId, criteria);

        // 작성일 포맷 + 게시글 카테고리 세팅
        replies.forEach(reply -> {
            reply.setCreatedDate(DateUtils.getCreatedDate(reply.getCreatedDatetime()));

            if (reply.getConsultationPost() != null && reply.getConsultationPost().getId() != null) {
                List<String> categories = consultationPostDAO.findCategoryNamesByPostId(reply.getConsultationPost().getId());
                reply.getConsultationPost().setCategoryNames(categories);
            }
        });
        
        DoctorDetailDTO doctorDetailDTO = new DoctorDetailDTO();
        doctorDetailDTO.setDoctorListDTO(doctor);
        doctorDetailDTO.setCounselReplyDTOList(replies);

        detailCriteriaDTO.setDoctorsDetail(List.of(doctorDetailDTO));
        detailCriteriaDTO.setCriteria(criteria);

        return detailCriteriaDTO;
    }

    @Override
    public DoctorCriteriaDTO getListAllStatus(Search search, String doctorStatus) {
        DoctorCriteriaDTO doctorCriteriaDTO = new DoctorCriteriaDTO();
        int total = doctorDAO.findCountAllStatus(search, doctorStatus);
        Criteria criteria = new Criteria(search.getPage(), total);
        List<DoctorDTO> doctorsList = doctorDAO.findAllStatus(criteria, doctorStatus, search);
        doctorsList.forEach(doctor -> {
            doctor.setCreatedDate(DateUtils.getCreatedDate(doctor.getCreatedDatetime()));
        });
        criteria.setHasMore(doctorsList.size() > criteria.getRowCount());
        if (criteria.isHasMore()) {
            doctorsList.remove(doctorsList.size() - 1);
        }
        doctorCriteriaDTO.setDoctorsList(doctorsList);
        doctorCriteriaDTO.setCriteria(criteria);
        doctorCriteriaDTO.setTotal(total);
        doctorCriteriaDTO.setSearch(search);
        return doctorCriteriaDTO;
    }

    @Override
    public Optional<DoctorHospitalDTO> getDoctorAdminById(Long doctorId) {
        Optional<DoctorHospitalDTO> doctor = doctorDAO.findDoctorById(doctorId);
        doctor.ifPresent(data -> {
            List<CounselReplyDTO> replies = counselReplyDAO.findTop3ConsultationPostsWithReplies(doctorId);
            replies.forEach(reply -> {
                reply.setCreatedDate(DateUtils.getCreatedDate(reply.getCreatedDatetime()));
            });
            data.setReplies(replies);
        });
        return doctor;
    }

    @Override
    public boolean approve(Long doctorId) {
        return doctorDAO.approveDoctor(doctorId) > 0;
    }

    @Override
    public void join(DoctorDTO doctorDTO, MemberDTO memberDTO, HospitalDTO hospitalDTO) {
        // 병원 상태 활성화
        hospitalDTO.setHospitalStatus(Status.ACTIVE.name());

        // 일반회원 정보 처리
        if (memberDTO != null && memberDTO.getId() != null) {

            doctorDTO.setMemberId(memberDTO.getId());
        } else {
            memberDTO.setRole(Role.DOCTOR);
            log.info("회원가입 직전 Role = {}", memberDTO.getRole());
            memberDTO = memberService.join(memberDTO);
            doctorDTO.setMemberId(memberDTO.getId());
        }

        //  병원 정보 추가
        Long hospitalId = hospitalService.register(hospitalDTO);
        doctorDTO.setHospitalId(hospitalId);


        // 의사 정보 추가
        doctorDTO.setMemberStatus(Status.ACTIVE);
        doctorDTO.setDoctorStatus(Status.ACTIVE);
        doctorDAO.insertDoctor(doctorDTO);

        log.info("Doctor 가입 완료 => memberId={}, hospitalId={}, license={}, specialty={}",
                doctorDTO.getMemberId(),
                doctorDTO.getHospitalId(),
                doctorDTO.getDoctorLicenseNumber(),
                doctorDTO.getDoctorSpecialty());
    }



}

