package com.pbcompass.alunos.mapper;

import com.pbcompass.alunos.dto.AlunoCadastrarDto;
import com.pbcompass.alunos.dto.AlunoRespostaDto;
import com.pbcompass.alunos.dto.BuscarTodosAlunosRespostaDto;
import com.pbcompass.alunos.entity.Aluno;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlunoMapper {

    public static AlunoRespostaDto toRespostaDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoRespostaDto.class);
    }

    public static Aluno toAluno(AlunoCadastrarDto dto) {
        return new ModelMapper().map(dto, Aluno.class);
    }

    public static BuscarTodosAlunosRespostaDto toBuscarTodosAlunosRespostaDto(Aluno aluno) {
        return new ModelMapper().map(aluno, BuscarTodosAlunosRespostaDto.class);
    }

    public static List<BuscarTodosAlunosRespostaDto> toListBuscarTodosAlunosRespostaDto(List<Aluno> alunos) {
        return alunos.stream().map(AlunoMapper::toBuscarTodosAlunosRespostaDto).toList();
    }
}
