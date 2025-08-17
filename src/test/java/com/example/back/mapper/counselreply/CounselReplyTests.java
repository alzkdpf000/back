package com.example.back.mapper.counselreply;

import com.example.back.repository.counselreply.CounselReplyDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CounselReplyTests {

    @Autowired
    CounselReplyMapper counselReplyMapper;
    @Autowired
    CounselReplyDAO counselReplyDAO;


    @Test
    public void testSelectTop3ConsultationPostsWithReplies(){
        log.info("{}",counselReplyMapper.selectTop3ConsultationPostsWithReplies(60L));
    }
    @Test
    public void testSelectTop3ConsultationPostsWithReplies2(){
        log.info("{}",counselReplyDAO.findTop3ConsultationPostsWithReplies(57L));
    }
}
