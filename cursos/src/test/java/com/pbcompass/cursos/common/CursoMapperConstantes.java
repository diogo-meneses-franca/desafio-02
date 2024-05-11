package com.pbcompass.cursos.common;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.entities.Professor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CursoMapperConstantes {

    public static final CursoCadastrarDto CURSO_CADASTRAR_DTO =
            new CursoCadastrarDto("Marcos", 80,
                    new ProfessorRespostaDto(1l, "PROFESSOR"), Curso.Area.TECNOLOGIAS);

    public static final CursoRespostaDto CURSO_RESPOSTA_DTO =
            new CursoRespostaDto(1L, "Marcos", 80,
                    new ProfessorRespostaDto(1l, "PROFESSOR"), true, Curso.Area.TECNOLOGIAS);

    public static final Curso CURSO =
            new Curso(1L, "Marcos", 80,
                    new Professor(1L, "PROFESSOR"), true, Curso.Area.TECNOLOGIAS);

    public static final List<Curso> LIST_CURSO = new ArrayList<>(Arrays.asList(CURSO));

}
