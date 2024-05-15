package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.dto.BuscarTodosCursosRespostaDto;
import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Curso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.pbcompass.cursos.common.CursoConstantes.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CursoMapperTest {

    @Test
    public void toCurso_ComCursoCadastrarDto_RetornarCurso() {
        CursoCadastrarDto testeCursoDto = CURSO_CADASTRAR_DTO;

        Curso testeCurso = CursoMapper.toCurso(testeCursoDto);

        assertThat(testeCurso.getNome()).isEqualTo(testeCursoDto.getNome());
        assertThat(testeCurso.getQuantidadeHoras()).isEqualTo(testeCursoDto.getQuantidadeHoras());
        assertThat(testeCurso.getProfessor().getId()).isEqualTo(testeCursoDto.getProfessor().getProfessorId());
        assertThat(testeCurso.getArea()).isEqualTo(testeCursoDto.getArea());
    }

    @Test
    public void toCurso_ComCursoRespostaDto_RetornarCurso() {
        CursoRespostaDto testeCursoDto = CURSO_RESPOSTA_DTO;

        Curso testeCurso = CursoMapper.toCurso(testeCursoDto);

        assertThat(testeCurso.getId()).isEqualTo(testeCursoDto.getId());
        assertThat(testeCurso.getNome()).isEqualTo(testeCursoDto.getNome());
        assertThat(testeCurso.getQuantidadeHoras()).isEqualTo(testeCursoDto.getQuantidadeHoras());
        assertThat(testeCurso.getProfessor().getId()).isEqualTo(testeCursoDto.getProfessor().getId());
        assertThat(testeCurso.isAtivo()).isEqualTo(testeCursoDto.isAtivo());
        assertThat(testeCurso.getArea()).isEqualTo(testeCursoDto.getArea());
        assertThat(testeCurso.getTotalAlunos()).isEqualTo(testeCursoDto.getTotalAlunos());
        assertThat(testeCurso.getAlunos()).isEqualTo(testeCursoDto.getAlunos());
    }

    @Test
    public void toRespostaDto_RetornarCursoRespostaDto() {
        Curso testeCurso = CURSO;

        CursoRespostaDto testeCursoDto = CursoMapper.toRespostaDto(testeCurso);

        assertThat(testeCursoDto.getId()).isEqualTo(testeCurso.getId());
        assertThat(testeCursoDto.getNome()).isEqualTo(testeCurso.getNome());
        assertThat(testeCursoDto.getQuantidadeHoras()).isEqualTo(testeCurso.getQuantidadeHoras());
        assertThat(testeCursoDto.getProfessor().getId()).isEqualTo(testeCurso.getProfessor().getId());
        assertThat(testeCursoDto.isAtivo()).isEqualTo(testeCurso.isAtivo());
        assertThat(testeCursoDto.getArea()).isEqualTo(testeCurso.getArea());
        assertThat(testeCursoDto.getTotalAlunos()).isEqualTo(testeCurso.getTotalAlunos());
        assertThat(testeCursoDto.getAlunos()).isEqualTo(testeCurso.getAlunos());
    }

    @Test
    public void toListaDto_RetornarListaDeCursoRespostaDto() {

        List<CursoRespostaDto> testeList =
                LIST_CURSO.stream()
                        .map(CursoMapper::toRespostaDto)
                        .toList();

        assertThat(testeList.size()).isEqualTo(LIST_CURSO.size());
        assertThat(testeList.get(0).getId()).isEqualTo(LIST_CURSO.get(0).getId());
        assertThat(testeList.get(0).getNome()).isEqualTo(LIST_CURSO.get(0).getNome());
        assertThat(testeList.get(0).getQuantidadeHoras()).isEqualTo(LIST_CURSO.get(0).getQuantidadeHoras());
        assertThat(testeList.get(0).getProfessor().getId()).isEqualTo(LIST_CURSO.get(0).getProfessor().getId());
        assertThat(testeList.get(0).isAtivo()).isEqualTo(LIST_CURSO.get(0).isAtivo());
        assertThat(testeList.get(0).getTotalAlunos()).isEqualTo(LIST_CURSO.get(0).getTotalAlunos());
        assertThat(testeList.get(0).getAlunos()).isEqualTo(LIST_CURSO.get(0).getAlunos());

    }

    @Test
    public void toBuscarTodosCursosRespostaDto_RetornaBuscarTodosCursosRespostaDto() {
        BuscarTodosCursosRespostaDto testeBuscarTodosDto = CursoMapper.toBuscarTodosCursosRespostaDto(CURSO);;

        assertThat(testeBuscarTodosDto.getId()).isEqualTo(CURSO.getId());
        assertThat(testeBuscarTodosDto.getNome()).isEqualTo(CURSO.getNome());
        assertThat(testeBuscarTodosDto.getQuantidadeHoras()).isEqualTo(CURSO.getQuantidadeHoras());
        assertThat(testeBuscarTodosDto.getProfessor().getId()).isEqualTo(CURSO.getProfessor().getId());
        assertThat(testeBuscarTodosDto.isAtivo()).isEqualTo(CURSO.isAtivo());
        assertThat(testeBuscarTodosDto.getArea()).isEqualTo(CURSO.getArea());
        assertThat(testeBuscarTodosDto.getTotalAlunos()).isEqualTo(CURSO.getTotalAlunos());

    }

    @Test
    public void toListBuscarTodosCursosRespostaDto_RetornarListaDeBuscarTodosCursosRespostaDto() {

        List<BuscarTodosCursosRespostaDto> testeList =
                LIST_CURSO.stream()
                        .map(CursoMapper::toBuscarTodosCursosRespostaDto)
                        .toList();

        assertThat(testeList.size()).isEqualTo(LIST_CURSO.size());
        assertThat(testeList.get(0).getId()).isEqualTo(LIST_CURSO.get(0).getId());
        assertThat(testeList.get(0).getNome()).isEqualTo(LIST_CURSO.get(0).getNome());
        assertThat(testeList.get(0).getQuantidadeHoras()).isEqualTo(LIST_CURSO.get(0).getQuantidadeHoras());
        assertThat(testeList.get(0).getProfessor().getId()).isEqualTo(LIST_CURSO.get(0).getProfessor().getId());
        assertThat(testeList.get(0).isAtivo()).isEqualTo(LIST_CURSO.get(0).isAtivo());
        assertThat(testeList.get(0).getTotalAlunos()).isEqualTo(LIST_CURSO.get(0).getTotalAlunos());

    }

}
