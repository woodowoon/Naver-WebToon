package com.project.naverwebtoon.domain.member.exception;

import com.project.naverwebtoon.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {

    NOT_ADMIN_MEMBER(600, HttpStatus.BAD_REQUEST, "관리자가 아닙니다."),
    NOT_WRITER_MEMBER(600, HttpStatus.BAD_REQUEST,"작가가 아닙니다."),
    NOT_MEMBER_ACTIVE(600, HttpStatus.BAD_REQUEST, "활성화된 회원이 아닙니다."),
    NOT_FOUND_MEMBER(601, HttpStatus.NOT_FOUND, "회원 정보가 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
