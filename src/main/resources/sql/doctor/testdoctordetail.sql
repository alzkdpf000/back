-- ===================== 카테고리 =====================
INSERT INTO tbl_category (category_name)
VALUES
    ('피부과'), ('안과'), ('치과'), ('비뇨의학과'), ('산부인과'), ('한방과'), ('신경과'),
    ('재활의학과'), ('정신건강의학과'), ('응급의학과'), ('마취통증의학과'), ('방사선종양학과'),
    ('영상의학과'), ('진단검사의학과'), ('직업환경의학과'), ('가정의학과'), ('소아청소년과'),
    ('이비인후과'), ('소화기내과'), ('호흡기내과'), ('감염내과'), ('알레르기내과'), ('내분비내과'),
    ('순환기내과'), ('신장내과'), ('류마티스내과'), ('혈액종양내과'), ('성형외과'), ('정형외과'),
    ('신경외과'), ('대장항문외과'), ('흉부외과'), ('외과');

-- ===================== 게시글 =====================
INSERT INTO tbl_consultation_post
(member_id, consultation_post_title, consultation_post_content, consultation_post_status, created_datetime, updated_datetime)
VALUES
    (31, '피부 트러블 관련 상담', '최근 얼굴에 여드름이 심해졌는데 치료 방법이 있을까요?', 'ACTIVE', NOW(), NOW()),
    (32, '눈이 자주 피로해요', '컴퓨터 작업 후 눈이 쉽게 피로해요. 어떻게 해야 하나요?', 'ACTIVE', NOW(), NOW()),
    (33, '치아 통증 문의', '최근 치아가 시리고 통증이 있어 상담드립니다.', 'ACTIVE', NOW(), NOW()),
    (34, '소화불량 고민', '식사 후 속이 자주 더부룩하고 불편합니다.', 'ACTIVE', NOW(), NOW()),
    (35, '운동 후 허리 통증', '운동 후 허리 통증이 심한데 치료 방법이 있을까요?', 'ACTIVE', NOW(), NOW()),
    (36, '수면 문제 상담', '최근 잠을 잘 못 자고 피로가 계속됩니다.', 'ACTIVE', NOW(), NOW()),
    (37, '감기 증상 관련 질문', '열과 기침이 있습니다. 치료가 필요할까요?', 'ACTIVE', NOW(), NOW()),
    (38, '피부 알레르기', '손등에 발진과 가려움이 있는데 어떻게 해야 하나요?', 'ACTIVE', NOW(), NOW()),
    (39, '임신 초기 상담', '임신 초기인데 복통과 출혈이 있습니다.', 'ACTIVE', NOW(), NOW()),
    (40, '심장 박동 이상', '가끔 심장이 빨리 뛰는 느낌이 듭니다.', 'ACTIVE', NOW(), NOW());

-- ===================== 답변글 =====================
INSERT INTO tbl_counsel_reply
(counsel_reply_content, counsel_reply_status, counsel_reply_acceptance, doctor_id, consultation_post_id, created_datetime, updated_datetime)
VALUES
    ('여드름 치료에는 스킨케어 관리와 필요시 약물치료가 도움이 됩니다.', 'active', 'accepted', 1, 1, NOW(), NOW()),
    ('눈 피로는 눈 운동과 휴식이 필요합니다.', 'active', 'accepted', 28, 2, NOW(), NOW()),
    ('치아 통증은 가까운 치과에서 정확한 검진을 받으시는 것이 좋습니다.', 'active', 'accepted', 28, 3, NOW(), NOW()),
    ('소화불량의 경우 식습관 조절과 필요시 내과 진료를 권장드립니다.', 'active', 'accepted', 1, 4, NOW(), NOW()),
    ('운동 후 허리 통증은 스트레칭과 물리치료가 도움이 될 수 있습니다.', 'active', 'accepted', 12, 5, NOW(), NOW()),
    ('수면 문제는 규칙적인 생활과 필요시 상담을 권장합니다.', 'active', 'accepted', 19, 6, NOW(), NOW()),
    ('감기 증상은 충분한 휴식과 수분 섭취가 필요합니다.', 'active', 'accepted', 6, 7, NOW(), NOW()),
    ('피부 알레르기는 항히스타민제 사용 및 병원 진료가 필요합니다.', 'active', 'accepted', 20, 8, NOW(), NOW()),
    ('임신 초기 복통과 출혈은 산부인과 방문이 필요합니다.', 'active', 'accepted', 11, 9, NOW(), NOW()),
    ('심장 박동 이상은 정확한 검진을 위해 내과 방문이 필요합니다.', 'active', 'accepted', 24, 10, NOW(), NOW());


-- ===================== 게시글-카테고리 매핑 =====================
INSERT INTO tbl_consultation_post_category (consultation_post_id, category_id)
VALUES
    (1, 1), -- 피부과
    (1,7),
    (2, 2),  -- 안과
    (3, 3),  -- 치과
    (4, 19), -- 소화기내과
    (5, 10), -- 외과
    (5,30),
    (6, 16), -- 가정의학과
    (7, 10), -- 응급의학과
    (8, 1),  -- 피부과
    (8,6),
    (9, 5),  -- 산부인과
    (10, 25); -- 순환기내과


select * from tbl_category;
select * from tbl_consultation_post;
select * from tbl_counsel_reply;
select * from tbl_consultation_post_category;