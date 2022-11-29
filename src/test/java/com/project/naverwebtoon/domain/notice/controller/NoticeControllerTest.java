package com.project.naverwebtoon.domain.notice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.naverwebtoon.domain.member.Account_info;
import com.project.naverwebtoon.domain.member.Grade;
import com.project.naverwebtoon.domain.member.Member;
import com.project.naverwebtoon.domain.notice.repository.NoticeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class NoticeControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired EntityManager em;
    @Autowired NoticeRepository noticeRepository;

    @Autowired ObjectMapper objectMapper;

    @Test
    public void Create_Notice_Controller_정상작동_테스트() throws Exception {
        // given
        Member member = createMember("admin", "123", "admin@", Grade.Admin, Account_info.Active);
        em.persist(member);

        Map<String, String> dto = new HashMap<>();
        dto.put("title", "title");
        dto.put("content", "content");
        dto.put("lock", "Private");

        // when
        mockMvc.perform(
                        post("/notice")
                                .param("memberId", member.getId())
                                .content(objectMapper.writeValueAsString(dto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        // then
        Assertions.assertThat(noticeRepository.findAll().size()).isEqualTo(1);
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
}
