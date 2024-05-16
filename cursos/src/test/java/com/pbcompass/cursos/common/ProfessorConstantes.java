package com.pbcompass.cursos.common;

import com.pbcompass.cursos.dto.ProfessorCadastrarDto;
import com.pbcompass.cursos.entities.Professor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfessorConstantes {

    public static final Professor PROFESSOR = new Professor(1L, "PROFESSOR");
    public static final Professor PROFESSOR_INVALIDO = new Professor(null, null);

    public static final ProfessorCadastrarDto PROFESSOR_CADASTRAR_DTO = new ProfessorCadastrarDto("PROFESSOR_CADASTRAR_DTO");

    public static final List<Professor> PROF_LIST = new ArrayList<Professor>(Arrays.asList(PROFESSOR));

}
