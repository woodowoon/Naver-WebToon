package com.project.naverwebtoon.domain.notice.service;

import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.member.exception.MemberExceptionType;
import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;

public interface NoticeService {

    /**
     * 게시글 등록
     */
    Long createNotice(CreateNoticeDto createNoticeDto, String memberId);

    /**
     * 권한이 무엇인지 return
     */
    Member checkAdmin(String memberId, MemberExceptionType memberExceptionType);

    void checkMemberIsNull(String memberId, MemberExceptionType memberExceptionType);

    void checkMemberActive(String memberId, MemberExceptionType memberExceptionType);
}
