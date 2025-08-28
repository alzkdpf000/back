package com.example.back.service.memberfile;

import com.example.back.dto.memberfile.MemberFileDTO;
import com.example.back.dto.memberfile.MemberProfileDTO;
import com.example.back.mapper.memberfile.MemberFileMapper;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemberFileServiceImpl implements MemberFileService {

    private final MemberFileMapper memberFileMapper;

    @Override
    public MemberProfileDTO getMemberProfile(Long memberId) {
        return memberFileMapper.getMemberProfile(memberId);
    }

    @Override
    public void uploadProfile(Long memberId, MultipartFile file) {
        if (file == null || file.isEmpty()) return;

//          저장 경로
        String todayPath = LocalDate.now().toString();
        String rootPath = "C:/file/" + todayPath;

//          디렉토리 없으면 생성
        File directory = new File(rootPath);
        if (!directory.exists()) directory.mkdirs();

//          UUID로 파일 이름 생성 (중복 방지)
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + "_" + file.getOriginalFilename();

        MemberFileDTO memberFileDTO = new MemberFileDTO();
        memberFileDTO.setFileOriginalName(file.getOriginalFilename());
        memberFileDTO.setFileName(newFileName);
        memberFileDTO.setFilePath(todayPath);
        memberFileDTO.setFileSize(String.valueOf(file.getSize()));

//        파일 저장
        memberFileMapper.insertFile(memberFileDTO);
        memberFileMapper.deleteMemberFile(memberId);

//        특정 회원 파일 추가
        memberFileMapper.insertMemberFile(memberId, memberFileDTO.getMemberId());

        try{
//            원본 업로드
            file.transferTo(new File(rootPath, newFileName));

//            썸네일 업로드
            if (file.getContentType() != null && file.getContentType().startsWith("image")) {
//                썸네일은 파일 이름 앞에 t_ 가 붙게 만들기 (구분)
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + newFileName));
                Thumbnailator.createThumbnail(file.getInputStream(), out, 100, 100);
                out.close();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }



    }
}
