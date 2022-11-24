package com.project.naverwebtoon.domain.member.repository;

import com.project.naverwebtoon.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // TODO : DataJPA 로 변경 예정
    public Member findById(String memberId) {
        return em.find(Member.class, memberId);
    }

}
