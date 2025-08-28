/*create database prjdb;
use prjdb;*/
use app;
show databases;
/* 병원 테이블 */

create table tbl_hospital
(
    id                    bigint unsigned auto_increment primary key,
    hospital_name         varchar(255) not null,
    hospital_phone        varchar(255) not null,
    hospital_homepage_url varchar(255),
    hospital_status       enum ('active', 'inactive') default 'active',
    created_datetime      datetime                    default current_timestamp,
    updated_datetime      datetime                    default current_timestamp
);
select *
from tbl_hospital;

/* 슈퍼 타입 회원 */
create table tbl_member
(
    id                       bigint unsigned auto_increment primary key,
    member_email             varchar(255) unique,
    member_password          varchar(255),
    member_name              varchar(255) not null,
    member_phone             varchar(255) not null,
    member_status            enum ('active','inactive')       default 'active',
    member_provider          enum ('kakao','goldentime')      default 'goldentime', /* 카카오 회원 구분 널이면 일반 카카오면 카카오 */
    member_kakao_email       varchar(255) unique, /* 카카오 이메일인데*/
    member_kakao_profile_url varchar(255), /* 카카오 프로필 url */
    member_role              enum ('doctor','member','admin') default 'member',
    member_vita_amount       int                              default 0,
    created_datetime         datetime                         default current_timestamp,
    updated_datetime         datetime                         default current_timestamp
);
select *
from tbl_member;


/*의사 테이블*/
create table tbl_doctor
(
    member_id             bigint unsigned primary key,
    doctor_license_number varchar(20)     not null unique,
    doctor_specialty      varchar(255)    not null,
    hospital_id           bigint unsigned not null,
    doctor_status         enum ('active','inactive') default 'inactive',
    constraint fk_doctor_hospital foreign key (hospital_id)
        references tbl_hospital (id),
    constraint fk_doctor_member foreign key (member_id)
        references tbl_member (id)
);

/* 일반 회원
create table tbl_normal_member
(
    id                     bigint unsigned primary key,
    normal_member_password varchar(255) not null,
    constraint fk_normal_member_member foreign key (id)
        references tbl_member (id)
);
*/
/* 상담글 */
create table tbl_consultation_post
(
    id                             bigint unsigned auto_increment primary key,
    consultation_post_title        varchar(255) not null,
    consultation_post_content      text         not null,
    consultation_post_status       enum ('active','inactive') default 'active',
    consultation_post_view_count   bigint unsigned            default 0,
    consultation_post_answer_count bigint unsigned            default 0,
    member_id                      bigint unsigned,
    created_datetime               datetime                   default current_timestamp,
    updated_datetime               datetime                   default current_timestamp,
    constraint fk_consultation_post_member foreign key (member_id)
        references tbl_member (id)
);

create table tbl_category
(
    id                   bigint unsigned auto_increment primary key,
    category_name        varchar(255),
    category_status enum ('active','inactive') default 'active',
    created_datetime     datetime                   default current_timestamp,
    updated_datetime     datetime                   default current_timestamp
);

create table tbl_consultation_post_category
(
    id                   bigint unsigned auto_increment primary key,
    category_id          bigint unsigned,
    consultation_post_id bigint unsigned,
    constraint fk_consultation_post_category_consultation_post foreign key (consultation_post_id)
        references tbl_consultation_post (id),
    constraint fk_consultation_post_category_category foreign key (category_id)
        references tbl_category (id)
);

/* 이미지 테이블 */
create table tbl_file
(
    id                 bigint unsigned auto_increment primary key,
    file_original_name varchar(255) not null,
    file_name          varchar(255) not null,
    file_path          varchar(255) not null,
    file_size          varchar(255) not null,
    created_datetime   datetime default current_timestamp,
    updated_datetime   datetime default current_timestamp
);
select * from tbl_file;
select * from tbl_member;
select * from tbl_member_file;
select * from tbl_consultation_post_file;
/* 상담글-이미지 관계 테이블 */
create table tbl_consultation_post_file
(
    file_id              bigint unsigned primary key,
    consultation_post_id bigint unsigned,
    constraint fk_consultation_post_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_consultation_post_file_consultation_post foreign key (consultation_post_id)
        references tbl_consultation_post (id)
);

/* 멤버-이미지 관계 테이블 */
create table tbl_member_file
(
    file_id   bigint unsigned primary key,
    member_id bigint unsigned unique,
    constraint fk_member_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_member_file_member foreign key (member_id)
        references tbl_member (id)

);

/* 병원-주소 테이블 */
create table tbl_hospital_address
(
    hospital_id      bigint unsigned primary key,
    road_address     varchar(255) not null,
    detail_address   varchar(255) not null,
    zip_code         char(5)      not null,
    address_status   enum ('active','inactive') default 'active',
    created_datetime datetime                   default current_timestamp,
    updated_datetime datetime                   default current_timestamp,
    constraint fk_hospital_address_hospital foreign key (hospital_id)
        references tbl_hospital (id)
);

/* 멤버-주소 관계 테이블 */
create table tbl_member_address
(
    member_id        bigint unsigned primary key,
    road_address     varchar(255) not null,
    detail_address   varchar(255) not null,
    zip_code         char(5)      not null,
    address_status   enum ('active','inactive') default 'active',
    created_datetime datetime                   default current_timestamp,
    updated_datetime datetime                   default current_timestamp,
    constraint fk_member_address_member foreign key (member_id)
        references tbl_member (id)
);

/* 리뷰 테이블*/
create table tbl_review
(
    id               bigint unsigned auto_increment primary key,
    review_rating    tinyint unsigned           default 0,
    review_content   text not null,
    review_status    enum ('active','inactive') default 'active',
    member_id        bigint unsigned,
    doctor_id        bigint unsigned,
    created_datetime datetime                   default current_timestamp,
    updated_datetime datetime                   default current_timestamp,
    constraint fk_review_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_review_doctor foreign key (doctor_id)
        references tbl_doctor (member_id)
);
/* 좋아요 테이블*/
create table tbl_likes
(
    id               bigint unsigned auto_increment primary key,
    member_id        bigint unsigned,
    doctor_id        bigint unsigned,
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp,
    constraint fk_likes_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_likes_doctor foreign key (doctor_id)
        references tbl_doctor (member_id)
);


/* 답변글 테이블 */
create table tbl_counsel_reply
(
    id                       bigint unsigned auto_increment primary key,
    counsel_reply_content    text not null,
    counsel_reply_status     enum ('active','inactive')     default 'active',
    counsel_reply_acceptance enum ('accepted','unaccepted') default 'unaccepted',
    doctor_id                bigint unsigned,
    consultation_post_id     bigint unsigned,
    created_datetime         datetime                       default current_timestamp,
    updated_datetime         datetime                       default current_timestamp,
    constraint fk_counsel_reply_doctor foreign key (doctor_id)
        references tbl_doctor (member_id),
    constraint fk_counsel_reply_consultation_post foreign key (consultation_post_id)
        references tbl_consultation_post (id)
);
/*alter table tbl_counsel_reply
    add counsel_reply_acceptance enum ('accepted','unaccepted') default 'unaccepted';*/
/* 답변글-이미지 테이블*/
create table tbl_counsel_reply_file
(
    file_id          bigint unsigned primary key,
    counsel_reply_id bigint unsigned,
    constraint fk_counsel_reply_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_counsel_reply_file_counsel_reply foreign key (counsel_reply_id)
        references tbl_counsel_reply (id)
);


/* 의사-이미지 테이블 */
create table tbl_doctor_file
(
    file_id   bigint unsigned primary key,
    doctor_id bigint unsigned unique,
    constraint fk_doctor_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_doctor_file_doctor foreign key (doctor_id)
        references tbl_doctor (member_id)
);
/* 병원-이미지 테이블 */
create table tbl_hospital_file
(
    file_id     bigint unsigned primary key,
    hospital_id bigint unsigned unique,
    constraint fk_hospital_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_hospital_file_hospital foreign key (hospital_id)
        references tbl_hospital (id)
);

/* 충전내역, 사용내역 */
create table tbl_vita_history
(
    id                        bigint unsigned auto_increment primary key,
    vita_history_amount       int                        default 0,
    vita_history_product_name VARCHAR(255)          not null,
    vita_history_result       enum ('done', 'cancel')    default 'done',
    vita_history_type         enum ('charge','use') not null,
    vita_history_status       enum ('active','inactive') default 'active',
    vita_history_description  varchar(255)          not null,
    member_id                 bigint unsigned,
    created_datetime          datetime                   default current_timestamp,
    updated_datetime          datetime                   default current_timestamp,
    constraint fk_charge_history_member foreign key (member_id)
        references tbl_member (id)
);

ALTER TABLE tbl_vita_history
    MODIFY vita_history_type ENUM ('charge','spend') NOT NULL;

/* 방문 진료 */
create table tbl_house_call
(
    id                              bigint unsigned auto_increment primary key,
    house_call_patient_name         varchar(255) not null,
    house_call_pain_level           tinyint                    default 1,
    house_call_patient_age          tinyint      not null,
    house_call_patient_gender       enum ('male','female')     default 'male',
    house_call_patient_phone        varchar(255) not null,
    house_call_preferred_visit_date datetime     not null,
    house_call_content              text         not null,
    house_call_status               enum ('active','inactive') default 'active',
    created_datetime                datetime                   default current_timestamp,
    updated_datetime                datetime                   default current_timestamp,
    member_id                       bigint unsigned,
    constraint fk_house_call_member foreign key (member_id)
        references tbl_member (id)
);

ALTER TABLE tbl_house_call
ADD COLUMN visit_status ENUM('completed', 'pending') DEFAULT 'pending' AFTER house_call_status;

create table tbl_house_call_doctor
(
    id            bigint unsigned auto_increment primary key,
    house_call_id bigint unsigned,
    doctor_id     bigint unsigned,
    constraint fk_house_call_doctor_house_call foreign key (house_call_id)
        references tbl_house_call (id),
    constraint fk_house_call_doctor_doctor foreign key (doctor_id)
        references tbl_doctor (member_id)
);


create table tbl_inquiries
(
    id                   bigint unsigned auto_increment primary key,
    inquiries_title      varchar(255) not null,
    inquiries_content    text         not null,
    inquiries_status     enum ('active','inactive') default 'active',
    inquiries_view_count bigint unsigned            default 0,
    member_id            bigint unsigned,
    created_datetime     datetime                   default current_timestamp,
    updated_datetime     datetime                   default current_timestamp,
    constraint fk_inquiries_member foreign key (member_id)
        references tbl_member (id)
);


create table tbl_inquiries_reply
(
    id                      bigint unsigned auto_increment primary key,
    inquiries_reply_content text not null,
    inquiries_status        enum ('active','inactive') default 'active',
    member_id               bigint unsigned,
    inquiries_id            bigint unsigned,
    created_datetime        datetime                   default current_timestamp,
    updated_datetime        datetime                   default current_timestamp,
    constraint fk_inquiries_reply_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_inquiries_reply_inquiries foreign key (inquiries_id)
        references tbl_inquiries (id)
);

create table tbl_notices
(
    id                 bigint unsigned auto_increment primary key,
    notices_title      varchar(255) not null,
    notices_content    text         not null,
    notices_status     enum ('active','inactive') default 'active',
    notices_view_count bigint unsigned            default 0,
    member_id          bigint unsigned,
    created_datetime   datetime                   default current_timestamp,
    updated_datetime   datetime                   default current_timestamp,
    constraint fk_notices_member foreign key (member_id)
        references tbl_member (id)
);


create table tbl_notices_file
(
    file_id    bigint unsigned primary key,
    notices_id bigint unsigned,
    constraint fk_notices_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_notices_file_hospital foreign key (notices_id)
        references tbl_notices (id)
);

create table tbl_inquiries_file
(
    file_id      bigint unsigned primary key,
    inquiries_id bigint unsigned,
    constraint fk_inquiries_file_file foreign key (file_id)
        references tbl_file (id),
    constraint fk_inquiries_file_inquiries foreign key (inquiries_id)
        references tbl_inquiries (id)
);


/* 병원-주소 테이블 */
create table tbl_house_call_address
(
    house_call_id    bigint unsigned primary key,
    road_address     varchar(255) not null,
    detail_address   varchar(255) not null,
    zip_code         char(5)      not null,
    address_status   enum ('active','inactive') default 'active',
    created_datetime datetime                   default current_timestamp,
    updated_datetime datetime                   default current_timestamp,
    constraint fk_house_call_address_hospital foreign key (house_call_id)
        references tbl_house_call (id)
);


create table tbl_payment
(
    id                     bigint unsigned auto_increment primary key,
    member_id              bigint unsigned,                                       -- 결제한 회원
    payment_method         varchar(50)    not null,                               -- 결제 수단 (우리는 카카오페이만 존재)
    payment_amount         decimal(10, 2) not null,                               -- 결제 금액 (실제 원화/달러)
    payment_status         enum ('pending','success','cancel') default 'success', -- pending, success, cancel
    payment_transaction_id varchar(100) unique,                                   -- pg사에서 내려오는 결제 번호
    created_datetime       datetime                            default current_timestamp,
    updated_datetime       datetime                            default current_timestamp,
    constraint fk_payment_member foreign key (member_id)
        references tbl_member (id)
);
alter table tbl_vita_history
    add column payment_id bigint unsigned null after member_id,
    add constraint fk_vita_payment foreign key (payment_id)
        references tbl_payment (id);



create table tbl_member_visited
(
    id           bigint unsigned auto_increment primary key,
    member_id    bigint unsigned,
    visited_datetime datetime default current_timestamp,
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp,
    constraint fk_member_visited_member foreign key (member_id)
        references tbl_member (id)
);
select * from tbl_member_visited;

drop table tbl_member_visited;