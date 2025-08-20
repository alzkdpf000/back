INSERT INTO tbl_member_visited (member_id, visited_datetime) VALUES
-- 멤버 83
(83, '2025-06-03 10:15:23'),
(83, '2025-07-07 14:20:11'),
(83, '2025-08-20 09:05:42'), -- 오늘 날짜

-- 멤버 84
(84, '2025-06-05 11:25:40'),
(84, '2025-07-15 16:40:55'),
(84, '2025-08-20 18:55:12'), -- 오늘 날짜

-- 멤버 85
(85, '2025-06-10 08:45:21'),
(85, '2025-07-18 19:33:10'),
(85, '2025-08-12 13:25:49'),

-- 멤버 86
(86, '2025-06-12 12:30:55'),
(86, '2025-07-22 20:45:33'),
(86, '2025-08-20 21:10:00'), -- 오늘 날짜

-- 멤버 87
(87, '2025-06-18 09:05:44'),
(87, '2025-07-25 15:41:50'),

-- 멤버 88
(88, '2025-06-20 21:18:10'),
(88, '2025-07-28 11:59:59'),
(88, '2025-08-05 17:45:30'),

-- 멤버 89
(89, '2025-06-22 07:40:30'),
(89, '2025-07-30 19:20:05'),
(89, '2025-08-20 10:33:21'), -- 오늘 날짜

-- 멤버 90
(90, '2025-06-25 16:55:44'),
(90, '2025-07-05 13:14:27'),
(90, '2025-08-15 08:05:05'),

-- 멤버 91
(91, '2025-06-28 18:40:20'),
(91, '2025-07-09 09:30:15'),
(91, '2025-08-20 12:20:47'), -- 오늘 날짜

-- 멤버 92
(92, '2025-07-03 14:55:42'),
(92, '2025-08-18 23:50:17');






/* 월 별 가입자 수 */
select date_format(created_datetime,'%Y-%m') as date,count(date_format(created_datetime,'%Y-%m')) as count
from tbl_member
group by date_format(created_datetime,'%Y-%m')
order by date desc;

/* 월 별  방문자 수 */
select date_format(visited_datetime,'%Y-%m') as date,count(date_format(visited_datetime,'%Y-%m')) as count
from tbl_member_visited
group by date_format(visited_datetime,'%Y-%m')
order by date desc;

/* 오늘 가입자 수 */
select count(id)
from tbl_member
where datediff(created_datetime,current_timestamp) = 0;
/* 오늘 방문자 수 */
select count(id)
from tbl_member_visited
where datediff(visited_datetime,current_timestamp) = 0;

select datediff(visited_datetime,current_timestamp)
from tbl_member_visited;