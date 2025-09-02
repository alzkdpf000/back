package com.example.back.controller.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/files/**")
@Slf4j
public class FileRestController {

    @GetMapping("display")
    public byte[] display(String filePath, String fileName) throws IOException {
//        File file = new File("C:/file/" + filePath, fileName);
        File file = new File("~/upload_file/" + filePath, fileName);
        log.info(fileName);
        log.info("{}, {}", file.getAbsolutePath(), file.exists());
        return FileCopyUtils.copyToByteArray(file);
    }
}
