package com.pbcompass.cursos.exceptions;

import com.pbcompass.cursos.exceptions.customizadas.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity<MensagemErroPadrao> entityNotFoundException(RuntimeException e,
                                                                      HttpServletRequest request){
        log.error("Api error ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErroPadrao(request, HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                              HttpServletRequest request, BindingResult result){
        log.error("Api error ", e);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo inválido", result));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErroPadrao> exception(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroPadrao(request, HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado, tente novamente mais tarde"));
    }

    @ExceptionHandler(LimiteMatriculasAtingidoException.class)
    public ResponseEntity<MensagemErroPadrao> limiteMatriculasAtingidoException(LimiteMatriculasAtingidoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage()));
    }

    @ExceptionHandler(CursoInativoException.class)
    public ResponseEntity<MensagemErroPadrao> cursoInativoException(CursoInativoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage()));
    }

    @ExceptionHandler(AlunoMatriculadoException.class)
    public ResponseEntity<MensagemErroPadrao> matriculaJaEfetuadaException(AlunoMatriculadoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErroPadrao(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }

    @ExceptionHandler(DadosDeCadastroInvalidosException.class)
    public ResponseEntity<MensagemErroPadrao> dadosDeCadastroInvalidosException(DadosDeCadastroInvalidosException e, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MensagemErroPadrao(request, HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(NomeDoCursoUnicoException.class)
    public ResponseEntity<MensagemErroPadrao> nomeDoCursoUnicoException(NomeDoCursoUnicoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensagemErroPadrao(request, HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(ErroComunicacaoEntreApisException.class)
    public ResponseEntity<MensagemErroPadrao> erroComunicacaoEntreApisException(ErroComunicacaoEntreApisException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroPadrao(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
