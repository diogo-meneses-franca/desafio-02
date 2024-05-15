package com.pbcompass.cursos.dto;

import com.pbcompass.cursos.entities.Curso.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoRespostaDto {

    private Long id;
    private String nome;
    private Integer quantidadeHoras;
    private ProfessorRespostaDto professor;
    private boolean ativo;
    private Area area;
    private Integer totalAlunos;
    private Set<AlunoRespostaDto> alunos;
}