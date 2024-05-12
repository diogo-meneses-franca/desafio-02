package com.pbcompass.alunos.common;

import com.pbcompass.alunos.dto.CursoMatricularDto;
import com.pbcompass.alunos.entity.Aluno;

import java.time.LocalDate;
import java.util.HashSet;

public class AlunoConstantes {

    public static final Aluno ALUNO = new Aluno(
            1L, "Mateus", "89456711021", LocalDate.parse("1999-09-09"), "M", new HashSet<>(), true
    );

    public static final Aluno ALUNO_INVALIDO = new Aluno(0L, "", "", LocalDate.now(), "", false);

}
