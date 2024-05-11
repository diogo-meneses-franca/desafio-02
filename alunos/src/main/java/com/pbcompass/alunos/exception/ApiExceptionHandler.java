package com.pbcompass.alunos.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(CpfUniqueViolationException.class)
    public ResponseEntity<MensagemDeErroPadrao> cpfUniqueViolationException(CpfUniqueViolationException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensagemDeErroPadrao(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemDeErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemDeErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, "Dados de entrada inválidos"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemDeErroPadrao> exception(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemDeErroPadrao(request, HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado, tente novamente mais tarde"));
    }
}
