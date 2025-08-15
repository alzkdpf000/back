-- 1. 멤버 1명 등록
INSERT INTO tbl_member (member_email, member_password, member_name, member_phone)
VALUES ('testuser@example.com', '1234', '테스트유저', '010-1234-5678');

-- 2. 카카오 멤버 1명 등록
INSERT INTO tbl_member (member_name, member_phone, member_provider, member_kakao_profile_url, member_kakao_email)
VALUES ('kakaouser', '010-1234-1243', 'kakao', 'kakaoProfile', 'test@kakao.com');

-- 2. 카테고리 전체 등록
INSERT INTO tbl_category (category_name)
VALUES ('피부과'),
       ('안과'),
       ('치과'),
       ('비뇨의학과'),
       ('산부인과'),
       ('한방과'),
       ('신경과'),
       ('재활의학과'),
       ('정신건강의학과'),
       ('응급의학과'),
       ('마취통증의학과'),
       ('방사선종양학과'),
       ('영상의학과'),
       ('진단검사의학과'),
       ('직업환경의학과'),
       ('가정의학과'),
       ('소아청소년과'),
       ('이비인후과'),
       ('소화기내과'),
       ('호흡기내과'),
       ('감염내과'),
       ('알레르기내과'),
       ('내분비내과'),
       ('순환기내과'),
       ('신장내과'),
       ('류마티스내과'),
       ('혈액종양내과'),
       ('성형외과'),
       ('정형외과'),
       ('신경외과'),
       ('대장항문외과'),
       ('흉부외과'),
       ('외과');

-- 3. 상담 게시글 예시 등록 (view_point 다양하게)
INSERT INTO tbl_consultation_post (member_id, consultation_post_title, consultation_post_content,
                                   consultation_post_view_count, consultation_post_answer_count)
VALUES (1, '첫 번째 상담글', '피부 고민에 대한 상담글입니다.', 10, 1),
       (1, '두 번째 상담글', '산부인과 관련 상담글입니다.', 50, 2),
       (2, '세 번째 상담글', '호흡기 문제에 대한 상담글입니다.', 10, 3),
       (2, '네 번째 상담글', '정형외과 수술 상담글입니다.', 100, 5),
       (1, '다섯 번째 상담글', '안과 검진 상담글입니다.', 25, 0);

-- 4. 게시글-카테고리 연결 (다중 연결 예시)
INSERT INTO tbl_consultation_post_category (category_id, consultation_post_id)
VALUES (1, 1),
       (3, 1),
       (5, 2),
       (10, 2),
       (20, 3),
       (7, 3),
       (28, 4),
       (30, 4),
       (2, 5),
       (25, 5);


-- 5. 파일 3개 등록
INSERT INTO tbl_file (file_name, file_path, file_size, file_original_name)
VALUES ('t_test.jpeg', '2025/08/13', 204800, 'test.jpeg'),
       ('t_user.jpeg', '2025/08/13', 307200, 'user.jpeg'),
       ('t_test.jpeg', '2025/08/13', 512000, 'test.jpeg');


-- 6. 파일-게시글 연결 (글 1에 3개 이미지 연결)
INSERT INTO tbl_consultation_post_file (file_id, consultation_post_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);


-- 7. tbl_file에 프로필 이미지 1개 넣기
INSERT INTO tbl_file (file_name, file_path, file_size, file_original_name)
VALUES ('t_user.jpeg', '2025/08/13', 150000, 'user.jpeg');

-- 8. 파일-멤버 연결
INSERT INTO tbl_member_file (file_id, member_id)
VALUES (4, 1);



explain
select tcp.id,
       tcp.consultation_post_title        as consultation_post_title,
       tcp.consultation_post_content      as consultation_post_content,
       tcp.consultation_post_view_count   as consultation_post_view_count,
       tcp.consultation_post_answer_count as consultation_post_answer_count,
       tcp.member_id                      as member_id,
       tcp.created_datetime               as created_date,
       IF(tm.member_provider = 'kakao', tm.member_kakao_profile_url, concat(vmf.file_path, '/', vmf.file_name))
                                          as member_file_path,
       tm.member_name                     as member_name
from tbl_member tm
         left outer join view_member_file vmf on tm.id = vmf.member_id
         join tbl_consultation_post tcp on tcp.member_id = tm.id
    and tcp.consultation_post_status = 'active'
order by consultation_post_view_count desc

limit 3 offset 0;


SHOW INDEX FROM tbl_member WHERE Column_name = 'id';
explain
select tcp.id,
       tcp.consultation_post_title        as consultation_post_title,
       tcp.consultation_post_content      as consultation_post_content,
       tcp.consultation_post_view_count   as consultation_post_view_count,
       tcp.consultation_post_answer_count as consultation_post_answer_count,
       tcp.member_id                      as member_id,
       tcp.created_datetime               as created_datetime,
       tm.member_provider                 as merber_provider,
       IF(tm.member_provider = 'kakao', tm.member_kakao_profile_url, concat(vmf.file_path, '/', vmf.file_path)),
       tm.member_name                     as member_name
from (SELECT id,
             consultation_post_title,
             consultation_post_content,
             consultation_post_view_count,
             consultation_post_answer_count,
             member_id,
             created_datetime
      FROM tbl_consultation_post
      WHERE consultation_post_status = 'active'
      LIMIT 5 offset 0) tcp
         JOIN tbl_member tm ON tcp.member_id = tm.id
         LEFT JOIN view_member_file vmf ON tm.id = vmf.member_id
ORDER BY tcp.consultation_post_view_count DESC
;


select count(*)
from tbl_consultation_post;


ANALYZE TABLE tbl_member;


SELECT id,
       consultation_post_title,
       consultation_post_content,
       consultation_post_view_count,
       consultation_post_answer_count,
       member_id,
       created_datetime
FROM tbl_consultation_post
WHERE consultation_post_status = 'active'
ORDER BY consultation_post_view_count DESC
LIMIT 3 offset 0;
