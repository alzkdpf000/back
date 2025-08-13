INSERT INTO tbl_member (member_email, member_password, member_name, member_phone, member_role)
VALUES
    ('kimdoc@email.com','pass123','김의사','010-1111-1111','doctor'),
    ('leedoc@email.com','pass123','이의사','010-2222-2222','doctor'),
    ('parkdoc@email.com','pass123','박의사','010-3333-3333','doctor'),
    ('choiuser@email.com','pass123','최사용','010-4444-4444','member'),
    ('junguser@email.com','pass123','정사용','010-5555-5555','member');

INSERT INTO tbl_hospital (id, hospital_name, hospital_phone)
VALUES
    (1,'서울중앙병원','02-123-4567'),
    (2,'부산의료원','051-987-6543'),
    (3,'대전종합병원','042-111-2222');

INSERT INTO tbl_hospital_address (hospital_id, road_address, detail_address, zip_code)
VALUES
    (1,'서울특별시 중구 세종대로 110','5층 내과','04524'),
    (2,'부산광역시 해운대구 해운대로 20','3층 외과','48058'),
    (3,'대전광역시 서구 둔산로 50','7층 내과','35222');

INSERT INTO tbl_doctor (member_id, doctor_license_number, doctor_specialty, doctor_status, hospital_id)
VALUES
    (1,'LIC123456','내과','ACTIVE',1),
    (2,'LIC789012','외과','ACTIVE',2),
    (3,'LIC345678','소아과','INACTIVE',3);

select * from tbl_member;
select * from tbl_doctor;
select * from tbl_hospital;
select * from tbl_hospital_address;

