INSERT INTO tbl_inquiries
(inquiries_title, inquiries_content, inquiries_status, inquiries_view_count, member_id)
VALUES ('로그인 오류 문의', '로그인이 되지 않습니다. 비밀번호를 재설정했는데도 동일한 문제가 발생합니다.', 'active', 15, 1),
       ('결제 취소 요청', '어제 결제한 상품을 취소하고 환불을 받고 싶습니다.', 'active', 8, 2),
       ('사이트 속도 저하', '최근 접속 속도가 매우 느려졌습니다. 특히 모바일에서 심합니다.', 'inactive', 23, 1),
       ('비밀번호 변경 방법', '비밀번호 변경 메뉴가 어디에 있는지 잘 모르겠습니다.', 'active', 5, 2),
       ('배송 지연 문의', '주문한 상품이 배송 예정일보다 5일 이상 지연되고 있습니다.', 'active', 19, 1),
       ('광고 메일 수신 거부', '계정 설정에서 광고 메일 수신 거부를 했는데도 계속 메일이 옵니다.', 'inactive', 3, 2),
       ('포인트 적립 오류', '최근 구매 건에 대해 포인트가 적립되지 않았습니다.', 'active', 7, 1),
       ('회원 탈퇴 후 재가입', '회원 탈퇴 후 다시 가입하려고 하는데 오류가 발생합니다.', 'active', 2, 2),
       ('게시글 삭제 요청', '제가 작성한 게시글을 삭제하고 싶습니다.', 'inactive', 11, 1),
       ('다국어 지원 여부', '해외 사용자도 사용할 수 있도록 다국어 지원이 가능한지 궁금합니다.', 'active', 14, 2);


INSERT INTO tbl_file (file_original_name, file_name, file_path, file_size)
VALUES ('test.jpeg', 't_test.jpeg', '2025/08/13', '204800'),
       ('user.jpeg', 't_user.jpeg', '2025/08/13', '307200'),
       ('test.jpeg', 't_test.jpeg', '2025/08/13', '512000'),
       ('user.jpeg', 't_user.jpeg', '2025/08/13', '150000');


INSERT INTO tbl_inquiries_file (file_id, inquiries_id)
VALUES (1, 1),
       (2, 1),
       (3, 3),
       (4, 5);



INSERT INTO tbl_inquiries_reply (inquiries_reply_content, member_id, inquiries_id)
VALUES ('안녕하세요, 해당 문제는 비밀번호 재설정 후 24시간 내에 반영됩니다.', 2, 1),
       ('환불 처리를 진행하겠습니다. 영업일 기준 3일 이내에 환불됩니다.', 1, 2),
       ('속도 저하 문제는 서버 점검 후 개선되었습니다.', 2, 3),
       ('비밀번호 변경은 설정 → 계정 관리에서 가능합니다.', 1, 4),
       ('배송이 지연되어 죄송합니다. 빠른 시일 내에 발송하겠습니다.', 2, 5),
       ('광고 메일 수신 거부가 정상적으로 적용되었습니다.', 1, 6),
       ('포인트 적립 오류를 확인했습니다. 누락분을 추가 적립하겠습니다.', 2, 7),
       ('재가입 문제는 고객센터로 문의 주시면 처리해 드리겠습니다.', 1, 8),
       ('다국어 지원 기능은 내년 상반기에 추가될 예정입니다.', 1, 10),
       ('추가로 로그인 시도 후에도 문제가 있으면 알려주세요.', 2, 1),
       ('배송이 내일 중으로 완료될 예정입니다.', 1, 5),
       ('현재 영어, 일본어, 중국어를 지원하고 있습니다.', 2, 10);


/* 목록에 뿌려줄 데이터들 */
explain
select ti.id,
       ti.inquiries_title                                                       as inquiries_title,
       ti.inquiries_content                                                     as inquiries_content,
       ti.created_datetime                                                      as created_datetime,
       if(tm.member_provider = 'kakao', tm.member_kakao_email, tm.member_email) as member_email,
       if(tir.id is not null, 1, 0)                                             as has_answer,
       tir.created_datetime                                                     as answer_datetie
from tbl_member tm
         join tbl_inquiries ti on ti.member_id = tm.id
         left outer join tbl_inquiries_reply tir on ti.id = tir.inquiries_id
    and ti.inquiries_status = 'active' and tir.inquiries_status = 'active'
order by ti.id desc;



select ti.id                                                                    as id,
       ti.inquiries_title                                                       as inquiries_title,
       ti.inquiries_content                                                     as inquiries_content,
       ti.created_datetime                                                      as created_datetime,
       if(tm.member_provider = 'kakao', tm.member_kakao_email, tm.member_email) as member_email,
       if(tir.id is not null, 1, 0)                                             as has_answer,
       tir.created_datetime                                                     as answer_datetie
from tbl_member tm
         join tbl_inquiries ti on ti.member_id = tm.id
         left outer join tbl_inquiries_reply tir on ti.id = tir.inquiries_id
    and ti.inquiries_status = 'active' and tir.inquiries_status = 'active'
order by ti.id desc;



explain
select sub.id                as id,
       sub.inquiries_title   as inquiries_title,
       sub.inquiries_content as inquiries_content,
       sub.created_datetime  as created_datetime,
       sub.member_email      as member_email,
       sub.has_answer        as has_answer,
       sub.answer_datetime   as answer_datetime
from (select ti.id,
             ti.inquiries_title,
             ti.inquiries_content,
             ti.created_datetime,
             if(tm.member_provider = 'kakao', tm.member_kakao_email, tm.member_email) as member_email,
             if(tir.id is not null, 1, 0)                                             as has_answer,
             tir.created_datetime                                                     as answer_datetime
      from tbl_member tm
               join tbl_inquiries ti
                    on ti.member_id = tm.id

               left join tbl_inquiries_reply tir
                         on ti.id = tir.inquiries_id
      where ti.inquiries_status = 'active'
        and tir.inquiries_status = 'active') sub
order by sub.id desc;


select sum(if(tir.id is not null, 1, 0)) as answer_count,
       sum(if(tir.id is null, 1, 0))     as no_answer_count
from tbl_inquiries ti
         left outer join tbl_inquiries_reply tir
                         on ti.id = tir.inquiries_id
                             and ti.inquiries_status = 'active';

explain
select ti.id                                                                    as id,
       ti.inquiries_title                                                       as inquiries_title,
       ti.inquiries_content                                                     as inquiries_content,
       ti.created_datetime                                                      as created_datetime,
       if(tm.member_provider = 'kakao', tm.member_kakao_email, tm.member_email) as member_email,
       tir.inquiries_reply_content                                              as inquiries_reply_content,
       ti.inquiries_status
from tbl_member tm
         join
     tbl_inquiries ti on tm.id = ti.member_id
         left outer join tbl_inquiries_reply tir
                         on ti.id = tir.inquiries_id
where ti.inquiries_status = 'active'
  and tir.inquiries_status = 'active';



select ti.id as id,
       ti.inquiries_title as inquiry_title,
       ti.inquiries_content as inquiry_content,
       ti.created_datetime as created_datetime,
       if(tm.member_provider = 'kakao', tm.member_kakao_email, tm.member_email) as member_email,
       tir.inquiries_reply_content as inquiry_reply_content
from tbl_member tm
         join
     tbl_inquiries ti on tm.id = ti.member_id
         left outer join tbl_inquiries_reply tir
                         on ti.id = tir.inquiries_id
where ti.id =7
  and ti.inquiries_status = 'active';

select *
from view_inquiry_member_reply;




select sub.id as id,
       sub.inquiries_title as inquiry_title,
       sub.inquiries_content as inquiry_content,
       sub.created_datetime as created_datetime,
       sub.member_email as member_email,
       sub.has_answer as has_answer,
       sub.answer_datetime as answer_datetime
from view_inquiry_member_reply sub
order by sub.id desc
limit 6 offset 0;


