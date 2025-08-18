insert into tbl_counsel_reply
(counsel_reply_content, counsel_reply_status, counsel_reply_acceptance, doctor_id, consultation_post_id)
values
('해당 증상은 단순 피로일 수 있으나, 지속된다면 병원 방문을 권장합니다.', 'active', 'accepted', 56, 1),
('약간의 소화불량 증세로 보이며, 식습관 개선이 필요해 보입니다.', 'active', 'unaccepted', 57, 2),
('혈액검사 결과에 따라 추가 진료가 필요할 수 있습니다.', 'active', 'accepted', 57, 3),
('최근 스트레스가 원인일 수 있습니다. 충분한 휴식을 취해보세요.', 'active', 'unaccepted', 58, 4),
('복용 중인 약의 영향 가능성이 있으니, 복용 내역을 확인해주세요.', 'active', 'accepted', 58, 5),
('만약 증상이 심해지면 응급실을 방문하시길 권장드립니다.', 'active', 'unaccepted', 58, 2);