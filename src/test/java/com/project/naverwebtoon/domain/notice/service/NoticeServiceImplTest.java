package com.project.naverwebtoon.domain.notice.service;

import com.project.naverwebtoon.domain.member.Account_info;
import com.project.naverwebtoon.domain.member.Grade;
import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.notice.Lockable;
import com.project.naverwebtoon.domain.notice.dto.CreateNoticeDto;
import com.project.naverwebtoon.domain.notice.repository.NoticeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
public class NoticeServiceImplTest {

    @Autowired private EntityManager em;

    @Autowired private NoticeService noticeService;
    @Autowired private NoticeRepository noticeRepository;

    private void clear() {
        em.flush();
        em.clear();
    }

    @Test
    public void 공지사항등록성공() throws Exception {
        // given
        Member member = createMember("admin", "123", "admin@", Grade.Admin, Account_info.Active);
        em.persist(member);
        CreateNoticeDto createNoticeDto = createNotice("title", "content", Lockable.Public);

        // when
        Long notice_id = noticeService.createNotice(createNoticeDto, "admin");
        clear();

        // then
        Assertions.assertEquals(notice_id, noticeRepository.findOne(notice_id).getNum());
    }

    @Test
    public void 저장실패_멤버권한_User_예외체크() throws Exception {
        // given
        Member member = createMember("user", "123", "user@", Grade.User, Account_info.Active);
        em.persist(member);
        CreateNoticeDto createNoticeDto = createNotice("title", "content", Lockable.Public);

        // when, then
        Assertions.assertThrows(Exception.class, () -> noticeService.createNotice(createNoticeDto, "user"));
    }

    @Test
    public void 저장실패_멤버권한_Writer_예외체크() throws Exception {
        // given
        Member member = createMember("writer", "123", "writer@", Grade.Writer, Account_info.Active);
        em.persist(member);
        CreateNoticeDto createNoticeDto = createNotice("title", "content", Lockable.Public);

        // when, then
        Assertions.assertThrows(Exception.class, () -> noticeService.createNotice(createNoticeDto, "writer"));
    }

    @Test
    public void 저장실패_멤버_info_frozen() throws Exception {
        // given
        Member member = createMember("admin", "123", "admin@", Grade.Admin, Account_info.Frozen);
        em.persist(member);
        CreateNoticeDto createNoticeDto = createNotice("title", "content", Lockable.Public);

        // when & then
        Assertions.assertThrows(Exception.class, () -> noticeService.createNotice(createNoticeDto, "admin"));
    }

    @Test
    public void 저장실패_content_Null_예외체크() throws Exception {
        // given
        Member member = createMember("admin", "123", "admin@", Grade.Admin, Account_info.Active);
        em.persist(member);
        CreateNoticeDto createNoticeDto = createNotice("title", null, Lockable.Public);

        // when, then
        Assertions.assertThrows(Exception.class, () -> noticeService.createNotice(createNoticeDto, "admin"));
    }

    @Test
    public void 저장실패_title_Null_예외체크() throws Exception {
        // given
        Member member = createMember("admin", "123", "admin@", Grade.Admin, Account_info.Active);
        em.persist(member);
        CreateNoticeDto createNoticeDto = createNotice(null, "content", Lockable.Public);

        // when, then
        Assertions.assertThrows(Exception.class, () -> noticeService.createNotice(createNoticeDto, "admin"));
    }

    private Member createMember(String Id, String pwd, String email, Grade grade, Account_info info) {
        Member Member = new Member();

        Member.setId(Id);
        Member.setPwd(pwd);
        Member.setEmail(email);
        Member.setGrade(grade);
        Member.setInfo(info);

        return Member;
    }

    private CreateNoticeDto createNotice(String title, String content, Lockable lock) {
        CreateNoticeDto createNoticeDto = new CreateNoticeDto();

        createNoticeDto.setTitle(title);
        createNoticeDto.setContent(content);
        createNoticeDto.setLock(lock);

        return createNoticeDto;
    }

}
