package com.pbcompass.cursos.dto;

import com.pbcompass.cursos.entities.Curso.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoCadastrarDto {

    private String nome;
    private Integer quantidadeHoras;
    private ProfessorCadastrarCursoDto professor;
    private Area area;
}