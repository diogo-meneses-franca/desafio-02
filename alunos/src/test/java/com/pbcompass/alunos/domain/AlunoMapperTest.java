package com.pbcompass.alunos.domain;

import com.pbcompass.alunos.dto.AlunoCadastrarDto;
import com.pbcompass.alunos.dto.AlunoRespostaDto;
import com.pbcompass.alunos.dto.BuscarTodosAlunosRespostaDto;
import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.mapper.AlunoMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.pbcompass.alunos.common.AlunoConstantes.ALUNO;
import static com.pbcompass.alunos.common.AlunoConstantes.LIST_AlUNOS;
import static org.assertj.core.api.Assertions.*;

public class AlunoMapperTest {

    @Test
    public void toRespostaDto_ComDadosValidos_RetornarAlunoRespostaDto() {
        Aluno testeAluno = new Aluno(1L ,"Mateus", "89456711021", LocalDate.parse("1999-09-09"), "M", true);
        AlunoRespostaDto testeDto = AlunoMapper.toRespostaDto(testeAluno);

        assertThat(testeDto.getId()).isEqualTo(testeAluno.getId());
        assertThat(testeDto.getNome()).isEqualTo(testeAluno.getNome());
        assertThat(testeDto.getSexo()).isEqualTo(testeAluno.getSexo());
        assertThat(testeDto.getAtivo()).isEqualTo(testeAluno.getAtivo());
    }

    @Test
    public void toAluno_ComDadosValidos_RetornarAluno() {
        AlunoCadastrarDto testeAlunoDto = new AlunoCadastrarDto("Mateus", "89456711021", LocalDate.parse("1999-09-09"), "M");
        Aluno testeAluno = AlunoMapper.toAluno(testeAlunoDto);

        assertThat(testeAluno.getNome()).isEqualTo(testeAlunoDto.getNome());
        assertThat(testeAluno.getCpf()).isEqualTo(testeAlunoDto.getCpf());
        assertThat(testeAluno.getDataNascimento()).isEqualTo(testeAlunoDto.getDataNascimento());
        assertThat(testeAluno.getSexo()).isEqualTo(testeAlunoDto.getSexo());
    }

    @Test
    public void toBuscarTodosAlunosRespostaDto_RetornarBuscarTodosAlunosRespostaDto() {

        BuscarTodosAlunosRespostaDto testeBuscarTodosDto = AlunoMapper.toBuscarTodosAlunosRespostaDto(ALUNO);

        assertThat(testeBuscarTodosDto.getId()).isEqualTo(ALUNO.getId());
        assertThat(testeBuscarTodosDto.getNome()).isEqualTo(ALUNO.getNome());
        assertThat(testeBuscarTodosDto.getSexo()).isEqualTo(ALUNO.getSexo());
        assertThat(testeBuscarTodosDto.getAtivo()).isEqualTo(ALUNO.getAtivo());
    }

    @Test
    public void toListBuscarTodosAlunosRespostaDto_RetornarListaDeBuscarTodosAlunosRespostaDto() {

        LIST_AlUNOS.add(ALUNO);

        List<BuscarTodosAlunosRespostaDto> testeBuscarTodosDto =
                LIST_AlUNOS.stream()
                        .map(AlunoMapper::toBuscarTodosAlunosRespostaDto)
                        .toList();

        assertThat(testeBuscarTodosDto.get(0).getId()).isEqualTo(ALUNO.getId());
        assertThat(testeBuscarTodosDto.get(0).getNome()).isEqualTo(ALUNO.getNome());
        assertThat(testeBuscarTodosDto.get(0).getSexo()).isEqualTo(ALUNO.getSexo());
        assertThat(testeBuscarTodosDto.get(0).getAtivo()).isEqualTo(ALUNO.getAtivo());
    }

}
