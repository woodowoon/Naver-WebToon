package com.project.naverwebtoon.domain.notice.controller;

import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;
import com.project.naverwebtoon.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    LocalDate now = LocalDate.now();

    // TODO : JWT 파싱해서 유저 정보를 가져와서 여기서 권한을 막는다.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/notice")
    public void create(
            @RequestBody @Valid CreateNoticeDto dto,
            @RequestParam String memberId) {

        noticeService.createNotice(dto, memberId);

    }

}
