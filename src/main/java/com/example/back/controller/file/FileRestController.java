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
        File file = new File("/Users/jeongchunghyo/Desktop/file/" + filePath, fileName);
        return FileCopyUtils.copyToByteArray(file);
    }
    @GetMapping("profile")
    public byte[] profile(String url) throws IOException {
        log.info("url:{}", url);
        File file = new File("/Users/jeongchunghyo/Desktop/file/",url);
        log.info("{}",file);
        return FileCopyUtils.copyToByteArray(file);
    }
}
