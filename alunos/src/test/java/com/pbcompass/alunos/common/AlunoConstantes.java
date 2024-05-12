package com.pbcompass.alunos.common;

import com.pbcompass.alunos.entity.Aluno;

import java.time.LocalDate;

public class AlunoConstantes {

    public static final Aluno ALUNO = new Aluno(1L, "Mateus", "89456711021", LocalDate.parse("1999-09-09"), "M", true);
    public static final Aluno ALUNO_INVALIDO = new Aluno(0L, "", "", LocalDate.now(), "", false);

}
