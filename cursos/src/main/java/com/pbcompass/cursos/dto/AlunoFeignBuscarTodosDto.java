package com.pbcompass.cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoFeignBuscarTodosDto {

    private Long id;
    private String nome;
    private String sexo;
}
