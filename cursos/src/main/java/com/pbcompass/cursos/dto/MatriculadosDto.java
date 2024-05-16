package com.pbcompass.cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculadosDto {

    private String nome;
    private String professor;
    private Integer totalAlunos;
    private Set<AlunoRespostaDto> alunos;
}
