package com.project.naverwebtoon.domain.notice.repository;

import com.project.naverwebtoon.domain.notice.Notice;
import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final EntityManager em;

    /**
     * 공지사항 저장
     */
    public void saveNotice(Notice notice) {
        em.persist(notice);
    }

}
