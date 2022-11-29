package com.project.naverwebtoon.domain.member.exception;

import com.project.naverwebtoon.global.exception.BaseException;
import com.project.naverwebtoon.global.exception.BaseExceptionType;

public class MemberException extends BaseException {

    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
