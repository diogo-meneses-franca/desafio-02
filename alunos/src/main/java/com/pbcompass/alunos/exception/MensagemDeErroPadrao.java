package com.pbcompass.alunos.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@ToString
public class MensagemDeErroPadrao {

    private String path;
    private String method;
    private int status;
    private String statusMensagem;
    private String mensagem;

    public MensagemDeErroPadrao(HttpServletRequest request, HttpStatus status, String mensagem) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusMensagem = status.getReasonPhrase();
        this.mensagem = mensagem;
    }
}
