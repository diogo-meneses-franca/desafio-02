package com.pbcompass.cursos.exceptions.customizadas;

public class ErroComunicacaoEntreApisException extends RuntimeException {

    public ErroComunicacaoEntreApisException(String mensagem) {
        super(mensagem);
    }
}
