create INDEX idx_member_file_member_id ON tbl_member_file(member_id);

CREATE INDEX idx_status_viewcount_desc
    ON tbl_consultation_post (consultation_post_status, consultation_post_view_count DESC);