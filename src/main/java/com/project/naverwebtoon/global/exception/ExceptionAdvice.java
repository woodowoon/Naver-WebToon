package com.project.naverwebtoon.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseEx(BaseException exception){
        log.error("BaseException errorMessage(): {}",exception.getExceptionType().getErrorMessage());
        log.error("BaseException errorCode(): {}",exception.getExceptionType().getErrorCode());

        return new ResponseEntity(new ExceptionDto(exception.getExceptionType().getErrorCode(), exception.getExceptionType().getErrorMessage()),exception.getExceptionType().getHttpStatus());
    }

    @Data
    @AllArgsConstructor
    static class ExceptionDto {
        private Integer errorCode;
        private String message;
    }
}
