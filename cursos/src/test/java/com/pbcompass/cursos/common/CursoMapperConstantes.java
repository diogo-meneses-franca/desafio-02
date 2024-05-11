package com.pbcompass.cursos.common;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.entities.Curso;

public class CursoMapperConstantes {

    public static final CursoCadastrarDto CURSO_CADASTRAR_DTO =
            new CursoCadastrarDto("Marcos", 80,
                    new ProfessorRespostaDto(1l, "PROFESSOR"), Curso.Area.TECNOLOGIAS);

    public static final CursoRespostaDto CURSO_RESPOSTA_DTO =
            new CursoRespostaDto(1L, "Marcos", 80,
                    new ProfessorRespostaDto(1l, "PROFESSOR"), true, Curso.Area.TECNOLOGIAS);

}
