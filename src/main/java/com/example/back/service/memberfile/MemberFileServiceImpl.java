package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.mapper.memberfile.MemberFileMapper;
import com.example.back.repository.memberfile.MemberFileDAO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MemberFileServiceImpl implements MemberFileService {

    private final MemberFileDAO memberFileDAO;

    @Override
    public MemberProfileDTO getMemberProfile(Long memberId) {
        return memberFileDAO.getMemberProfile(memberId);
    }

    @Override
    public void deleteFileByMemberId(Long memberId) {
        if (memberId != null) {
            memberFileDAO.deleteFileByMemberId(memberId);
        }

    }

    @Override
    public void insertFile(Long memberId, MultipartFile file) {


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberFileDTO update(Long memberId, MultipartFile file) {
        if(file.getOriginalFilename().isEmpty()){
            return null;
        }
        String rootPath = "c:/file/" + getPath();
//        String rootPath = "/Users/taemin/Desktop/file/" + getPath();
        MemberFileDTO memberFileDTO = new MemberFileDTO();

        if(memberFileDAO.getMemberProfile(memberId) != null){
            memberFileDAO.deleteMemberFile(memberId);
            memberFileDAO.deleteFileByMemberId(memberId);
        }

        UUID uuid = UUID.randomUUID();

        memberFileDTO.setMemberId(memberId);
        memberFileDTO.setFileOriginalName(file.getOriginalFilename());
        memberFileDTO.setFilePath(getPath());
        memberFileDTO.setFileName(uuid + "_" + file.getOriginalFilename());
        memberFileDTO.setFileSize(String.valueOf(file.getSize()));

        memberFileDAO.insertFile(memberFileDTO);
        memberFileDAO.insertMemberFile(memberId, memberFileDTO.getFileId());

        File directory = new File(rootPath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        try {
            file.transferTo(new File(rootPath + "/" + memberFileDTO.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memberFileDTO;

    }
}
