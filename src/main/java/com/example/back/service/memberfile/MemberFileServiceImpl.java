package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.mapper.memberfile.MemberFileMapper;
import com.example.back.repository.memberfile.MemberFileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberFileServiceImpl implements MemberFileService {

    private final MemberFileDAO memberFileDAO;

    @Override
    public MemberProfileDTO getMemberProfile(Long memberId) {
        return memberFileDAO.getMemberProfile(memberId);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
//    정상 실행종료되면 commit, 예외 발생 시 rollback
//    MultipartFile - 단일 첨부파일 설정
    public MemberFileDTO update(Long memberId, MultipartFile file) {
//        파일 유효성 검사 - 업로드된 파일이 없으면 종료
        if(file.getOriginalFilename().isEmpty()){
            return null;
        }
//        파일 저장 경로 설정
//        String rootPath = "c:/file/" + getPath();
        String rootPath = "/home/ubuntu/upload_file/" + getPath();
        MemberFileDTO memberFileDTO = new MemberFileDTO();
        MemberProfileDTO memberProfile = memberFileDAO.getMemberProfile(memberId);
//        기존에 있는 프로필 삭제 - DB에 해당 회원이 파일을 가지고 있다면 memberFile, file 둘다 삭제
        if(memberProfile != null){
            memberFileDAO.deleteMemberFile(memberId);
            memberFileDAO.deleteFileByFileId(memberProfile.getFileId());
        }

        UUID uuid = UUID.randomUUID();

//        새 파일 UUID로 중복되지 않게 생성
//        원본 이름, 경로, 파일 이름, 사이즈를 DTO에 저장
        memberFileDTO.setMemberId(memberId);
        memberFileDTO.setFileOriginalName(file.getOriginalFilename());
        memberFileDTO.setFilePath(getPath());
        memberFileDTO.setFileName(uuid + "_" + file.getOriginalFilename());
        memberFileDTO.setFileSize(String.valueOf(file.getSize()));

//        파일 추가
        memberFileDAO.insertFile(memberFileDTO);
        log.info("저장한 파일 정보{}",memberFileDTO);
        memberFileDAO.insertMemberFile(memberId, memberFileDTO.getFileId());

//        rootPath 폴더가 없으면 새로 생성
        File directory = new File(rootPath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        try {
//            transferTo: 지정된 경로에 파일 생성
//            업로드된 파일을 지정된 폴더에 저장
            file.transferTo(new File(rootPath + "/" + memberFileDTO.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memberFileDTO;

    }
}
