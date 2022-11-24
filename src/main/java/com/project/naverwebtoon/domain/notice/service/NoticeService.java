package com.project.naverwebtoon.domain.notice.service;

import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;

public interface NoticeService {

    /**
     * 게시글 등록
     */
    void createNotice(CreateNoticeDto createNoticeDto, String memberId) throws Exception;

    /**
     * 권한이 무엇인지 return
     */
    Member checkAdmin(String memberId) throws Exception;

}
