package com.project.naverwebtoon.domain.notice.dto;

import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.notice.Lockable;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class CreateNoticeDto {
    private Long num;
    private Member member_id;
    private LocalDateTime upload_date;
    @NotBlank(message = "title 값은 필수 입니다.")
    private String title;
    @NotBlank(message = "content 값은 필수 입니다.")
    private String content;
    private Lockable lock;
}
