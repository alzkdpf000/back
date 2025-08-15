
INSERT INTO tbl_notices (notices_title, notices_content, notices_status, notices_view_count, member_id)
VALUES
    ('시스템 점검 안내2', '8월 20일 02:00 ~ 04:00 서버 점검이 진행됩니다.', 'active', 12, 1),
    ('이벤트 당첨자 발표2', '여름맞이 이벤트 당첨자를 발표합니다.', 'active', 45, 2),
    ('신규 서비스 출시2', '새로운 결제 서비스를 출시했습니다.', 'active', 5, 1),
    ('정기 점검 안내2', '8월 25일 01:00 ~ 03:00 정기 점검 예정입니다.', 'active', 3, 1),
    ('앱 업데이트 안내2', '앱 v2.1 업데이트가 배포되었습니다.', 'active', 20, 2),
    ('고객센터 운영시간 변경2', '추석 연휴로 인해 고객센터 운영시간이 변경됩니다.', 'active', 7, 2),
    ('보안 패치 공지2', '중요 보안 패치가 적용됩니다.', 'active', 15, 2),
    ('서버 이전 안내2', '서버 이전 작업으로 일시적인 접속 장애가 발생할 수 있습니다.', 'active', 11, 1),
    ('신규 기능 소개2', '마이페이지에 프로필 배경 설정 기능이 추가되었습니다.', 'active', 9, 2),
    ('이벤트 예고2', '다음 달 대규모 할인 이벤트가 진행됩니다.', 'active', 30, 2);


INSERT INTO tbl_file (file_original_name, file_name, file_path, file_size)
VALUES
    ('notice2_user.jpeg', 't_user.jpeg', '2025/08/13', '307200'),
    ('notice2_test.jpeg', 't_test.jpeg', '2025/08/13', '512000');


INSERT INTO tbl_notices_file (file_id, notices_id)
VALUES
    (9, 11),
    (10, 12),
    (11, 11),
    (12, 11);



select notices_title, created_datetime
    from tbl_notices
where notices_status = 'active';