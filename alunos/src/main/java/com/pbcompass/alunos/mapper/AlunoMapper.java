package com.pbcompass.alunos.mapper;

import com.pbcompass.alunos.dto.AlunoCriarDto;
import com.pbcompass.alunos.dto.AlunoRespostaDto;
import com.pbcompass.alunos.entity.Aluno;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlunoMapper {

    public static AlunoRespostaDto toRespostaDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoRespostaDto.class);
    }

    public static Aluno toAluno(AlunoCriarDto dto) {
        return new ModelMapper().map(dto, Aluno.class);
    }
}
