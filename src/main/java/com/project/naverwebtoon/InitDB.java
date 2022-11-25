package com.project.naverwebtoon;

import com.project.naverwebtoon.domain.member.Account_info;
import com.project.naverwebtoon.domain.member.Grade;
import com.project.naverwebtoon.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.insert_admin_member();
        initService.insert_user_member();
        initService.insert_writer_member();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void insert_admin_member() {
            Member member = createMember("adminA", "1234", "adminA@com", Grade.Admin, Account_info.Active);

            em.persist(member);
        }

        public void insert_user_member() {
            Member member = createMember("userA", "1234", "userA@com", Grade.User, Account_info.Active);

            em.persist(member);
        }

        public void insert_writer_member() {
            Member member = createMember("writerA", "1234", "writer@com", Grade.Writer, Account_info.Active);

            em.persist(member);
        }

        private Member createMember(String Id, String pwd, String email, Grade grade, Account_info info) {
            Member admin_Member = new Member();

            admin_Member.setId(Id);
            admin_Member.setPwd(pwd);
            admin_Member.setEmail(email);
            admin_Member.setGrade(grade);
            admin_Member.setInfo(info);

            return admin_Member;
        }
    }
}
