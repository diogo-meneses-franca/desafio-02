package com.pbcompass.alunos.exception;

import jakarta.persistence.EntityNotFoundException;
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

    @ExceptionHandler(CpfUnicoException.class)
    public ResponseEntity<MensagemErroPadrao> cpfUnicoException(CpfUnicoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensagemErroPadrao(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, "Dados de entrada inválidos"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErroPadrao> exception(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroPadrao(request, HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado, tente novamente mais tarde"));
    }

    @ExceptionHandler(AlunoMatriculadoException.class)
    public ResponseEntity<MensagemErroPadrao> matriculaJaEfetuadaException(AlunoMatriculadoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }

    @ExceptionHandler(FalhaAoMatricularAlunoException.class)
    public ResponseEntity<MensagemErroPadrao> falhaAoMatricularAlunoException(FalhaAoMatricularAlunoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensagemErroPadrao> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroPadrao(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
}
