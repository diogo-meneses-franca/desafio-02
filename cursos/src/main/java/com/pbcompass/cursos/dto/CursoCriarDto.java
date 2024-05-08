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
public class CursoCriarDto {

    private String nome;
    private Integer quantidadeHoras;
    private ProfessorRespostaDto professor;
    private Area area;
}