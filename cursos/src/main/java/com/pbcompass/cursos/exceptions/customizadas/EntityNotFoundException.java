package com.pbcompass.cursos.exceptions.customizadas;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg){
        super(msg);
    }
}
