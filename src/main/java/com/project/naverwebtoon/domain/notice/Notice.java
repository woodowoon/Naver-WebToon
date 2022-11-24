package com.project.naverwebtoon.domain.notice;

import com.project.naverwebtoon.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter // TODO: 나중에 무조건 없앤다 내가 이거 무조건 두고봐라 세터 싹다 없애주마!!!!
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_num")
    private Long num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member adminId;

    @Column(nullable = false)
    private LocalDateTime upload_date;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    private Long hitCount;

    @Enumerated(EnumType.STRING)
    private Lockable lock;


}
