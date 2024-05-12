package com.pbcompass.cursos.exceptions.customizadas;

public class DadosDeCadastroInvalidosException extends RuntimeException{

    public DadosDeCadastroInvalidosException(String mensagem) {
        super(mensagem);
    }
}
