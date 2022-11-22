package com.project.naverwebtoon.domain.notice;

import com.project.naverwebtoon.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
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
    @Lob
    private String content;

    @Column(nullable = false)
    private String title;

    private Long hitCount;

    @Enumerated(EnumType.STRING)
    private Lockable lock;

}
