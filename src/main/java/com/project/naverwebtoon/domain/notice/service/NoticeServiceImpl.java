package com.project.naverwebtoon.domain.notice.service;

import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.member.exception.MemberException;
import com.project.naverwebtoon.domain.member.exception.MemberExceptionType;
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
    public Long createNotice(CreateNoticeDto createNoticeDto, String memberId){
        // 이렇게 오류를 일일이 다 불러오지 않고 처음부터 처리해줄 수 있는 방법은 없을까??
        // 처음부터 오류에 관한 내용을 싹다 처리해버리는 거지.
        // 근데, 그러려면 memberId 를 넘겨야되는데 어디서 넘기지??
        checkMemberIsNull(memberId, MemberExceptionType.NOT_FOUND_MEMBER);
        checkMemberActive(memberId, MemberExceptionType.NOT_MEMBER_ACTIVE);
        Member adminMember = checkAdmin(memberId, MemberExceptionType.NOT_ADMIN_MEMBER);
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

        return notice.getNum();
    }

    // TODO : 예외처리는 JPA Data 배우게 되면 그때 orElseThrow 활용
    @Override
    public Member checkAdmin(String ID, MemberExceptionType memberExceptionType) {
        Member memberId = memberRepository.findById(ID);
        if (! memberId.getGrade().toString().equals("Admin")) {
            throw new MemberException(memberExceptionType);
        }
        return memberId;
    }

    @Override
    public void checkMemberIsNull(String ID, MemberExceptionType memberExceptionType) {
        Member memberId = memberRepository.findById(ID);
        if(memberId == null) {
            throw new MemberException(memberExceptionType);
        }
    }

    @Override
    public void checkMemberActive(String ID, MemberExceptionType memberExceptionType) {
        Member memberID = memberRepository.findById(ID);
        if(! memberID.getInfo().toString().equals("Active")) {
            throw new MemberException(memberExceptionType);
        }
    }
}
