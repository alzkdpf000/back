package com.example.back.controller.consultationpost;

import com.example.back.dto.member.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class ConsultationPostController {
    private final HttpSession session;

    @GetMapping("/list")
    public String goPostList() {
        return "post/post-list";
    }

}
