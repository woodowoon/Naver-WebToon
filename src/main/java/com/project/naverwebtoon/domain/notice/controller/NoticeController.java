package com.project.naverwebtoon.domain.notice.controller;

import com.project.naverwebtoon.domain.notice.Lockable;
import com.project.naverwebtoon.domain.notice.Notice;
import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;
import com.project.naverwebtoon.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    LocalDate now = LocalDate.now();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/notice")
    public void create(
            @RequestBody @Valid CreateNoticeDto dto,
            @RequestParam String memberId) throws Exception {

        noticeService.createNotice(dto, memberId);

    }

}
