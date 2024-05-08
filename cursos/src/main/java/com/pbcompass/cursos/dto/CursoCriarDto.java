package com.pbcompass.cursos.dto;

import com.pbcompass.cursos.entities.Curso.Area;

public record CursoCriarDto(

        String nome,
        Integer quantidadeHoras,
        ProfessorDto professor,
        Area area) {
}