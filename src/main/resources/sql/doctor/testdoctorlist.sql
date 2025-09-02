INSERT INTO tbl_hospital (id, hospital_name, hospital_phone)
VALUES
    (1,'서울중앙병원','02-123-4567'),
    (2,'부산의료원','051-987-6543'),
    (3,'대전종합병원','042-111-2222'),
    (4,'인천사랑병원','032-555-1212'),
    (5,'광주광역병원','062-777-8888');

INSERT INTO tbl_hospital_address (hospital_id, road_address, detail_address, zip_code)
VALUES
    (1,'서울특별시 중구 세종대로 110','5층 내과','04524'),
    (2,'부산광역시 해운대구 해운대로 20','3층 외과','48058'),
    (3,'대전광역시 서구 둔산로 50','7층 내과','35222'),
    (4,'인천광역시 연수구 송도과학로 30','2층 소아과','21999'),
    (5,'광주광역시 동구 금남로 15','4층 외과','61111');

INSERT INTO tbl_member (member_email, member_password, member_name, member_phone, member_role)
VALUES
    ('doc1@email.com','pass123','김의사1','010-1111-1111','doctor'),
    ('doc2@email.com','pass123','이의사2','010-1111-2222','doctor'),
    ('doc3@email.com','pass123','박의사3','010-1111-3333','doctor'),
    ('doc4@email.com','pass123','최의사4','010-1111-4444','doctor'),
    ('doc5@email.com','pass123','정의사5','010-1111-5555','doctor'),
    ('doc6@email.com','pass123','한의사6','010-1111-6666','doctor'),
    ('doc7@email.com','pass123','오의사7','010-1111-7777','doctor'),
    ('doc8@email.com','pass123','서의사8','010-1111-8888','doctor'),
    ('doc9@email.com','pass123','신의사9','010-1111-9999','doctor'),
    ('doc10@email.com','pass123','권의사10','010-1111-0000','doctor'),
    ('doc11@email.com','pass123','조의사11','010-1122-1111','doctor'),
    ('doc12@email.com','pass123','윤의사12','010-1122-2222','doctor'),
    ('doc13@email.com','pass123','강의사13','010-1122-3333','doctor'),
    ('doc14@email.com','pass123','남의사14','010-1122-4444','doctor'),
    ('doc15@email.com','pass123','심의사15','010-1122-5555','doctor'),
    ('doc16@email.com','pass123','백의사16','010-1122-6666','doctor'),
    ('doc17@email.com','pass123','임의사17','010-1122-7777','doctor'),
    ('doc18@email.com','pass123','서의사18','010-1122-8888','doctor'),
    ('doc19@email.com','pass123','황의사19','010-1122-9999','doctor'),
    ('doc20@email.com','pass123','차의사20','010-1122-0000','doctor'),
    ('doc21@email.com','pass123','강의사21','010-1133-1111','doctor'),
    ('doc22@email.com','pass123','오의사22','010-1133-2222','doctor'),
    ('doc23@email.com','pass123','김의사23','010-1133-3333','doctor'),
    ('doc24@email.com','pass123','이의사24','010-1133-4444','doctor'),
    ('doc25@email.com','pass123','박의사25','010-1133-5555','doctor'),
    ('doc26@email.com','pass123','최의사26','010-1133-6666','doctor'),
    ('doc27@email.com','pass123','정의사27','010-1133-7777','doctor'),
    ('doc28@email.com','pass123','한의사28','010-1133-8888','doctor'),
    ('doc29@email.com','pass123','오의사29','010-1133-9999','doctor'),
    ('doc30@email.com','pass123','서의사30','010-1133-0000','doctor'),
    ('user1@email.com','pass123','김사용1','010-1144-1111','member'),
    ('user2@email.com','pass123','이사용2','010-1144-2222','member'),
    ('user3@email.com','pass123','박사용3','010-1144-3333','member'),
    ('user4@email.com','pass123','최사용4','010-1144-4444','member'),
    ('user5@email.com','pass123','정사용5','010-1144-5555','member'),
    ('user6@email.com','pass123','한사용6','010-1144-6666','member'),
    ('user7@email.com','pass123','오사용7','010-1144-7777','member'),
    ('user8@email.com','pass123','서사용8','010-1144-8888','member'),
    ('user9@email.com','pass123','신사용9','010-1144-9999','member'),
    ('user10@email.com','pass123','권사용10','010-1144-0000','member'),
    ('user11@email.com','pass123','조사용11','010-1155-1111','member'),
    ('user12@email.com','pass123','윤사용12','010-1155-2222','member'),
    ('user13@email.com','pass123','강사용13','010-1155-3333','member'),
    ('user14@email.com','pass123','남사용14','010-1155-4444','member'),
    ('user15@email.com','pass123','심사용15','010-1155-5555','member'),
    ('user16@email.com','pass123','백사용16','010-1155-6666','member'),
    ('user17@email.com','pass123','임사용17','010-1155-7777','member'),
    ('user18@email.com','pass123','서사용18','010-1155-8888','member'),
    ('user19@email.com','pass123','황사용19','010-1155-9999','member'),
    ('user20@email.com','pass123','차사용20','010-1155-0000','member');

INSERT INTO tbl_doctor (member_id, doctor_license_number, doctor_specialty, doctor_status, hospital_id)
VALUES
    (4,'LIC10004','내과','ACTIVE',4),
    (5,'LIC10005','외과','ACTIVE',5),
    (6,'LIC10006','소아과','INACTIVE',1),
    (7,'LIC10007','정형외과','ACTIVE',2),
    (8,'LIC10008','피부과','ACTIVE',3),
    (9,'LIC10009','치과','ACTIVE',4),
    (10,'LIC10010','내과','ACTIVE',5),
    (11,'LIC10011','외과','ACTIVE',1),
    (12,'LIC10012','소아과','ACTIVE',2),
    (13,'LIC10013','정형외과','INACTIVE',3),
    (14,'LIC10014','피부과','ACTIVE',4),
    (15,'LIC10015','치과','ACTIVE',5),
    (16,'LIC10016','내과','ACTIVE',1),
    (17,'LIC10017','외과','ACTIVE',2),
    (18,'LIC10018','소아과','ACTIVE',3),
    (19,'LIC10019','정형외과','INACTIVE',4),
    (20,'LIC10020','피부과','ACTIVE',5),
    (21,'LIC10021','치과','ACTIVE',1),
    (22,'LIC10022','내과','ACTIVE',2),
    (23,'LIC10023','외과','ACTIVE',3),
    (24,'LIC10024','소아과','ACTIVE',4),
    (25,'LIC10025','정형외과','ACTIVE',5),
    (26,'LIC10026','피부과','ACTIVE',1),
    (27,'LIC10027','치과','ACTIVE',2),
    (28,'LIC10028','내과','ACTIVE',3),
    (29,'LIC10029','외과','ACTIVE',4),
    (30,'LIC10030','소아과','ACTIVE',5);



INSERT INTO tbl_likes (member_id, doctor_id, created_datetime, updated_datetime)
VALUES
-- user1
(4, 1, NOW(), NOW()), (31, 5, NOW(), NOW()), (31, 9, NOW(), NOW()), (31, 12, NOW(), NOW()), (31, 20, NOW(), NOW()),
-- user2
(5, 2, NOW(), NOW()), (32, 4, NOW(), NOW()), (32, 6, NOW(), NOW()), (32, 15, NOW(), NOW()), (32, 25, NOW(), NOW()),
-- user3
(6, 3, NOW(), NOW()), (33, 7, NOW(), NOW()), (33, 11, NOW(), NOW()), (33, 19, NOW(), NOW()),
-- user4
(7, 1, NOW(), NOW()), (34, 2, NOW(), NOW()), (34, 8, NOW(), NOW()), (34, 14, NOW(), NOW()), (34, 22, NOW(), NOW()),
-- user5
(8, 5, NOW(), NOW()), (35, 10, NOW(), NOW()), (35, 15, NOW(), NOW()), (35, 20, NOW(), NOW()),
-- user6
(9, 3, NOW(), NOW()), (36, 6, NOW(), NOW()), (36, 9, NOW(), NOW()), (36, 12, NOW(), NOW()), (36, 18, NOW(), NOW()),
-- user7
(10, 4, NOW(), NOW()), (37, 7, NOW(), NOW()), (37, 11, NOW(), NOW()), (37, 16, NOW(), NOW()),
-- user8
(11, 2, NOW(), NOW()), (38, 5, NOW(), NOW()), (38, 8, NOW(), NOW()), (38, 13, NOW(), NOW()), (38, 21, NOW(), NOW()),
-- user9
(12, 1, NOW(), NOW()), (39, 6, NOW(), NOW()), (39, 12, NOW(), NOW()), (39, 18, NOW(), NOW()),
-- user10
(13, 3, NOW(), NOW()), (40, 9, NOW(), NOW()), (40, 15, NOW(), NOW()), (40, 20, NOW(), NOW()),
-- user11
(14, 2, NOW(), NOW()), (41, 4, NOW(), NOW()), (41, 8, NOW(), NOW()), (41, 10, NOW(), NOW()), (41, 22, NOW(), NOW()),
-- user12
(15, 1, NOW(), NOW()), (42, 5, NOW(), NOW()), (42, 7, NOW(), NOW()), (42, 11, NOW(), NOW()), (42, 19, NOW(), NOW()),
-- user13
(16, 3, NOW(), NOW()), (43, 6, NOW(), NOW()), (43, 9, NOW(), NOW()), (43, 14, NOW(), NOW()),
-- user14
(17, 2, NOW(), NOW()), (44, 4, NOW(), NOW()), (44, 8, NOW(), NOW()), (44, 13, NOW(), NOW()), (44, 20, NOW(), NOW()),
-- user15
(18, 1, NOW(), NOW()), (45, 5, NOW(), NOW()), (45, 10, NOW(), NOW()), (45, 15, NOW(), NOW()),
-- user16
(19, 3, NOW(), NOW()), (46, 6, NOW(), NOW()), (46, 12, NOW(), NOW()), (46, 18, NOW(), NOW()),
-- user17
(20, 4, NOW(), NOW()), (47, 7, NOW(), NOW()), (47, 11, NOW(), NOW()), (47, 16, NOW(), NOW()),
-- user18
(21, 2, NOW(), NOW()), (48, 5, NOW(), NOW()), (48, 8, NOW(), NOW()), (48, 13, NOW(), NOW()), (48, 21, NOW(), NOW()),
-- user19
(22, 1, NOW(), NOW()), (49, 6, NOW(), NOW()), (49, 12, NOW(), NOW()), (49, 18, NOW(), NOW()),
-- user20
(23, 3, NOW(), NOW()), (50, 9, NOW(), NOW()), (50, 15, NOW(), NOW()), (50, 20, NOW(), NOW());


select * from tbl_likes;

select * from tbl_member;
select * from tbl_doctor;
select * from tbl_hospital;
select * from tbl_hospital_address;

SELECT * FROM tbl_likes WHERE doctor_id = 30 AND member_id = 1;
SELECT * FROM tbl_doctor WHERE tbl_doctor.member_id = 30;
SELECT * FROM tbl_member WHERE id = 50;

SELECT * FROM tbl_doctor WHERE member_id = 31;
SELECT * FROM tbl_member WHERE id = 31;
SELECT * FROM tbl_likes WHERE doctor_id = 30 AND member_id = 1;

select id,
       member_email,
       member_name,
       member_phone,
       member_status,
       member_provider,
       member_kakao_email,
       member_vita_amount,
       created_datetime
from view_member_doctor
where member_role = 'doctor';
#   and doctor_status = 'active';


