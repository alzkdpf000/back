use app;



create view view_member_file as
(
select tmf.file_id,
       tmf.member_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join
     tbl_member_file tmf on tf.id = tmf.file_id
    );

create view view_hospital_file as
(
select tdhf.file_id,
       tdhf.hospital_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join tbl_hospital_file tdhf on
    tf.id = tdhf.file_id
    );


create view view_doctor_file as
(
select tdf.file_id,
       tdf.doctor_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join tbl_doctor_file tdf on
    tf.id = tdf.file_id
    );


create view view_consultation_post_file as
(
select tcpf.file_id,
       tcpf.consultation_post_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join tbl_consultation_post_file tcpf on
    tf.id = tcpf.file_id
    );


create view view_counsel_reply_file as
(
select tcrf.file_id,
       tcrf.counsel_reply_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join tbl_counsel_reply_file tcrf on
    tf.id = tcrf.file_id
    );


create view view_notices_file as
(
select tnf.file_id,
       tnf.notices_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join tbl_notices_file tnf on
    tf.id = tnf.file_id
    );

create view view_inquiries_file as
(
select tif.file_id,
       tif.inquiries_id,
       tf.file_name,
       tf.file_path,
       tf.file_size,
       tf.file_status,
       tf.created_date,
       tf.updated_date
from tbl_file tf
         join tbl_inquiries_file tif on
    tf.id = tif.file_id
    );

create view view_member_doctor as
(
select tm.id,
       tm.member_email,
       tm.member_password,
       tm.member_name,
       tm.member_phone,
       tm.member_status,
       tm.member_provider,
       tm.member_kakao_email,
       tm.member_kakao_profile_url,
       tm.member_role,
       tm.member_vita_amount,
       tm.created_date,
       tm.updated_date,
       td.doctor_license_number,
       td.doctor_specialty,
       td.hospital_id
from tbl_member tm
         join tbl_doctor td
              on tm.id = td.member_id
    );