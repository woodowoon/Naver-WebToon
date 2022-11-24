package com.project.naverwebtoon.domain.notice.service;

import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.member.repository.MemberRepository;
import com.project.naverwebtoon.domain.notice.Notice;
import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;
import com.project.naverwebtoon.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService{

    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;

    @Override
    public void createNotice(CreateNoticeDto createNoticeDto, String memberId) throws Exception {
        Member adminMember = checkAdmin(memberId);
        createNoticeDto.setMember_id(adminMember);

        Notice notice = new Notice();
        Long hitCount = 0L; // 초기 hit 값

        notice.setAdminId(createNoticeDto.getMember_id());
        notice.setContent(createNoticeDto.getContent());
        notice.setLock(createNoticeDto.getLock());
        notice.setTitle(createNoticeDto.getTitle());
        notice.setUpload_date(LocalDateTime.now());
        notice.setHitCount(hitCount);

        noticeRepository.saveNotice(notice);
    }

    @Override
    public Member checkAdmin(String ID) throws Exception {
        return memberRepository.findById(ID);
    }
}
