package com.pbcompass.cursos.dto;

import com.pbcompass.cursos.entities.Curso.Area;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoCadastrarDto {

    @NotBlank
    private String nome;

    @NotBlank
    private Integer quantidadeHoras;

    private ProfessorCadastrarCursoDto professor;

    @NotBlank
    private Area area;
}