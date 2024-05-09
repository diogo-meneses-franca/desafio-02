package com.pbcompass.alunos.common;

import com.pbcompass.alunos.entity.Aluno;

import java.time.LocalDate;

public class AlunoConstantes {

    public static final Aluno ALUNO = new Aluno("Mateus", "89456711021", LocalDate.parse("1999-09-09"), "M");
    public static final Aluno ALUNO_INVALIDO = new Aluno("", "", LocalDate.now(), "");

}
