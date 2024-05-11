package com.pbcompass.cursos.common;

import com.pbcompass.cursos.entities.Curso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CursoConstantes {

    public static final Curso CURSO = new Curso(1L, "TADS", 20, null, true, Curso.Area.TECNOLOGIAS);
    public static final Curso CURSO_INVALIDO = new Curso(null, null, null, null, true, null);

    public static final List<Curso> LIST_CURSO = new ArrayList<Curso>(Arrays.asList(CURSO));

}
