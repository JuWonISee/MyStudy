package com.firstassignment.common.handler;

import com.firstassignment.exception.MemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage()) // "이메일 형식이 올바르지 않습니다."
                .findFirst()
                .orElse("잘못된 요청입니다.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<String> handleMemberException(MemberException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}