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
#     (48,'LIC10001','내과','ACTIVE',1)
#     (49,'LIC10002','외과','ACTIVE',2),
#     (50,'LIC10003','소아과','INACTIVE',3),
    (51,'LIC10004','정형외과','ACTIVE',1),
    (52,'LIC10005','피부과','ACTIVE',2),
    (53,'LIC10006','치과','ACTIVE',3),
    (54,'LIC10007','내과','ACTIVE',4),
    (55,'LIC10008','외과','ACTIVE',5),
    (56,'LIC10009','소아과','INACTIVE',1),
    (57,'LIC10010','정형외과','ACTIVE',2),
    (58,'LIC10011','피부과','ACTIVE',3),
    (59,'LIC10012','치과','ACTIVE',4),
    (60,'LIC10013','내과','ACTIVE',5),
    (61,'LIC10014','외과','ACTIVE',1),
    (62,'LIC10015','소아과','ACTIVE',2),
    (63,'LIC10016','정형외과','INACTIVE',3),
    (64,'LIC10017','피부과','ACTIVE',4),
    (65,'LIC10018','치과','ACTIVE',5),
    (66,'LIC10019','내과','ACTIVE',1),
    (67,'LIC10020','외과','ACTIVE',2),
    (68,'LIC10021','소아과','ACTIVE',3),
    (69,'LIC10022','정형외과','INACTIVE',4),
    (70,'LIC10023','피부과','ACTIVE',5),
    (71,'LIC10024','치과','ACTIVE',1),
    (72,'LIC10025','내과','ACTIVE',2),
    (73,'LIC10026','외과','ACTIVE',3),
    (74,'LIC10027','소아과','ACTIVE',4),
    (75,'LIC10028','정형외과','ACTIVE',5),
    (76,'LIC10029','피부과','ACTIVE',1),
    (77,'LIC10030','치과','ACTIVE',2);;



INSERT INTO tbl_likes (member_id, doctor_id, created_datetime, updated_datetime) VALUES
     (31,1,NOW(),NOW()),(31,2,NOW(),NOW()),(31,4,NOW(),NOW()),(31,5,NOW(),NOW()),(31,6,NOW(),NOW()),
     (31,7,NOW(),NOW()),(31,8,NOW(),NOW()),(31,10,NOW(),NOW()),(31,11,NOW(),NOW()),(31,12,NOW(),NOW()),
     (31,13,NOW(),NOW()),(31,14,NOW(),NOW()),(31,15,NOW(),NOW()),(31,17,NOW(),NOW()),(31,18,NOW(),NOW()),
     (31,19,NOW(),NOW()),(31,20,NOW(),NOW()),(31,21,NOW(),NOW()),(31,23,NOW(),NOW()),(31,24,NOW(),NOW()),
     (31,25,NOW(),NOW()),(31,26,NOW(),NOW()),(31,27,NOW(),NOW()),(31,28,NOW(),NOW()),(31,29,NOW(),NOW()),
     (31,30,NOW(),NOW()),
     (32,1,NOW(),NOW()),(32,2,NOW(),NOW()),(32,4,NOW(),NOW()),(32,5,NOW(),NOW()),(32,6,NOW(),NOW()),
     (32,7,NOW(),NOW()),(32,8,NOW(),NOW()),(32,10,NOW(),NOW()),(32,11,NOW(),NOW()),(32,12,NOW(),NOW()),
     (32,13,NOW(),NOW()),(32,14,NOW(),NOW()),(32,15,NOW(),NOW()),(32,17,NOW(),NOW()),(32,18,NOW(),NOW()),
     (32,19,NOW(),NOW()),(32,20,NOW(),NOW()),(32,21,NOW(),NOW()),(32,23,NOW(),NOW()),(32,24,NOW(),NOW()),
     (32,25,NOW(),NOW()),(32,26,NOW(),NOW()),(32,27,NOW(),NOW()),(32,28,NOW(),NOW()),(32,29,NOW(),NOW()),
     (32,30,NOW(),NOW()),
     (33,1,NOW(),NOW()),(33,2,NOW(),NOW()),(33,4,NOW(),NOW()),(33,5,NOW(),NOW()),(33,6,NOW(),NOW()),
     (33,7,NOW(),NOW()),(33,8,NOW(),NOW()),(33,10,NOW(),NOW()),(33,11,NOW(),NOW()),(33,12,NOW(),NOW()),
     (33,13,NOW(),NOW()),(33,14,NOW(),NOW()),(33,15,NOW(),NOW()),(33,17,NOW(),NOW()),(33,18,NOW(),NOW()),
     (33,19,NOW(),NOW()),(33,20,NOW(),NOW()),(33,21,NOW(),NOW()),(33,23,NOW(),NOW()),(33,24,NOW(),NOW()),
     (33,25,NOW(),NOW()),(33,26,NOW(),NOW()),(33,27,NOW(),NOW()),(33,28,NOW(),NOW()),(33,29,NOW(),NOW()),
     (33,30,NOW(),NOW()),(50,1,NOW(),NOW()),(50,2,NOW(),NOW()),(50,4,NOW(),NOW()),(50,5,NOW(),NOW()),
     (50,6,NOW(),NOW()),
     (50,7,NOW(),NOW()),(50,8,NOW(),NOW()),(50,10,NOW(),NOW()),(50,11,NOW(),NOW()),(50,12,NOW(),NOW()),
     (50,13,NOW(),NOW()),(50,14,NOW(),NOW()),(50,15,NOW(),NOW()),(50,17,NOW(),NOW()),(50,18,NOW(),NOW()),
     (50,19,NOW(),NOW()),(50,20,NOW(),NOW()),(50,21,NOW(),NOW()),(50,23,NOW(),NOW()),(50,24,NOW(),NOW()),
     (50,25,NOW(),NOW()),(50,26,NOW(),NOW()),(50,27,NOW(),NOW()),(50,28,NOW(),NOW()),(50,29,NOW(),NOW()),
     (50,30,NOW(),NOW());


select * from tbl_likes;

select * from tbl_member;
select * from tbl_doctor;
select * from tbl_hospital;
select * from tbl_hospital_address;
<<<<<<< HEAD

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
=======
>>>>>>> doctor/list
