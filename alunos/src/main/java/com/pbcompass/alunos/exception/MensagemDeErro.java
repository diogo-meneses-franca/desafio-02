package com.pbcompass.alunos.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@ToString
public class MensagemDeErro {

    private String path;
    private String method;
    private int status;
    private String statusMensagem;
    private String mensagem;

    private Map<String, String> erros = new HashMap<>();

    public MensagemDeErro(HttpServletRequest request, HttpStatus status, String mensagem) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusMensagem = status.getReasonPhrase();
        this.mensagem = mensagem;
    }

    public MensagemDeErro(HttpServletRequest request, HttpStatus status, String mensagem, BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusMensagem = status.getReasonPhrase();
        this.mensagem = mensagem;
        addErrors(result);
    }

    private void addErrors(BindingResult result) {
        for(FieldError error : result.getFieldErrors()) {
            erros.put(error.getField(), error.getDefaultMessage());
        }
    }
}
