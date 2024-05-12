package com.pbcompass.alunos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoRespostaDto {

    private Long id;
    private String nome;
    private Integer quantidadeHoras;
    private ProfessorRespostaDto professor;
    private boolean ativo;
    private String area;
}
