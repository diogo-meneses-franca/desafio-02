package com.pbcompass.alunos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoCadastrarDto {

    @NotBlank
    @Size(max = 150)
    private String nome;

    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 1, max = 1)
    @Pattern(regexp = "[M|F]")
    private String sexo;
}
