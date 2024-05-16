package com.pbcompass.alunos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuscarTodosAlunosRespostaDto {

    private Long id;
    private String nome;
    private String sexo;
    private Boolean ativo;
}
