package com.project.naverwebtoon.domain.notice.dto;

import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.notice.Lockable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateNoticeDto {
    private Long num;
    private Member member_id;
    private LocalDateTime upload_date;
    private String title;
    private String content;
    private Lockable lock;


}
