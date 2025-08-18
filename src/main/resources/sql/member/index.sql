# create fulltext index member_name_full_index on tbl_member (member_name)
#     with parser ngram;
# # member_email, member_kakao_email
# create fulltext index member_email_full_index on tbl_member (member_email)
#     with parser ngram;
#
# create fulltext index member_kakao_email_full_index on tbl_member (member_kakao_email)
#     with parser ngram;
create fulltext index member_full_index on tbl_member (member_name,member_email,member_kakao_email)
    with parser ngram;

explain select id,
       member_email,

       member_name,
       member_phone,
       member_status,
       member_provider as provider,
       member_kakao_email,
       created_datetime
from tbl_member
where member_role = 'member'
            and MATCH(member_name,member_email,member_kakao_email) AGAINST('테스트유저');


SHOW INDEX FROM tbl_member;


SHOW VARIABLES LIKE 'ft_min_word_len';



INSERT INTO tbl_member (
    member_email,
    member_password,
    member_name,
    member_phone,
    member_status,
    member_provider,
    member_kakao_email,
    member_kakao_profile_url,
    member_role,
    member_vita_amount,
    created_datetime,
    updated_datetime
) VALUES
      (NULL, NULL, '카카오유저1', '010-1000-0001', 'active', 'kakao', 'kakao22@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:00:00', '2025-08-18 10:00:00'),
      (NULL, NULL, '카카오유저2', '010-1000-0002', 'active', 'kakao', 'kakao2@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:01:00', '2025-08-18 10:01:00'),
      (NULL, NULL, '카카오유저3', '010-1000-0003', 'active', 'kakao', 'kakao3@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:02:00', '2025-08-18 10:02:00'),
      (NULL, NULL, '카카오유저4', '010-1000-0004', 'active', 'kakao', 'kakao4@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:03:00', '2025-08-18 10:03:00'),
      (NULL, NULL, '카카오유저5', '010-1000-0005', 'active', 'kakao', 'kakao5@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:04:00', '2025-08-18 10:04:00'),
      (NULL, NULL, '카카오유저6', '010-1000-0006', 'active', 'kakao', 'kakao6@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:05:00', '2025-08-18 10:05:00'),
      (NULL, NULL, '카카오유저7', '010-1000-0007', 'active', 'kakao', 'kakao7@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:06:00', '2025-08-18 10:06:00'),
      (NULL, NULL, '카카오유저8', '010-1000-0008', 'active', 'kakao', 'kakao8@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:07:00', '2025-08-18 10:07:00'),
      (NULL, NULL, '카카오유저9', '010-1000-0009', 'active', 'kakao', 'kakao9@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:08:00', '2025-08-18 10:08:00'),
      (NULL, NULL, '카카오유저10', '010-1000-0010', 'active', 'kakao', 'kakao10@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:09:00', '2025-08-18 10:09:00'),
      (NULL, NULL, '카카오유저11', '010-1000-0011', 'active', 'kakao', 'kakao11@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:10:00', '2025-08-18 10:10:00'),
      (NULL, NULL, '카카오유저12', '010-1000-0012', 'active', 'kakao', 'kakao12@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:11:00', '2025-08-18 10:11:00'),
      (NULL, NULL, '카카오유저13', '010-1000-0013', 'active', 'kakao', 'kakao13@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:12:00', '2025-08-18 10:12:00'),
      (NULL, NULL, '카카오유저14', '010-1000-0014', 'active', 'kakao', 'kakao14@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:13:00', '2025-08-18 10:13:00'),
      (NULL, NULL, '카카오유저15', '010-1000-0015', 'active', 'kakao', 'kakao15@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:14:00', '2025-08-18 10:14:00'),
      (NULL, NULL, '카카오유저16', '010-1000-0016', 'active', 'kakao', 'kakao16@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:15:00', '2025-08-18 10:15:00'),
      (NULL, NULL, '카카오유저17', '010-1000-0017', 'active', 'kakao', 'kakao17@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:16:00', '2025-08-18 10:16:00'),
      (NULL, NULL, '카카오유저18', '010-1000-0018', 'active', 'kakao', 'kakao18@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:17:00', '2025-08-18 10:17:00'),
      (NULL, NULL, '카카오유저19', '010-1000-0019', 'active', 'kakao', 'kakao19@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:18:00', '2025-08-18 10:18:00'),
      (NULL, NULL, '카카오유저20', '010-1000-0020', 'active', 'kakao', 'kakao20@kakao.com', 'https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp', 'member', 0, '2025-08-18 10:19:00', '2025-08-18 10:19:00');

