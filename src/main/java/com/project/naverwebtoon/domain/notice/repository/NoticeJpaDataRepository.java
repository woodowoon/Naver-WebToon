package com.project.naverwebtoon.domain.notice.repository;

import com.project.naverwebtoon.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO : 추후, JPA DATA 활용 예정
public interface NoticeJpaDataRepository extends JpaRepository<Notice, Long> {

}
