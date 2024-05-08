package com.pbcompass.cursos.dto;

import com.pbcompass.cursos.entities.Curso.Area;

public record CursoRespostaDto(
        Long id,
        String nome,
        Integer quantidadeHoras,
        ProfessorDto professor,
        boolean ativo,
        Area area) {
}