package com.pbcompass.alunos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoMatricularDto {

    @NotBlank
    private Long alunoId;
    private boolean ativo;
}
