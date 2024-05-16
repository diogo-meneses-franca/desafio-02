package com.pbcompass.alunos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pbcompass.alunos.entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRespostaDto {

    private Long id;
    private String nome;
    private String sexo;
    private Boolean ativo;
    private Set<CursoRespostaDto> matriculas;
}
