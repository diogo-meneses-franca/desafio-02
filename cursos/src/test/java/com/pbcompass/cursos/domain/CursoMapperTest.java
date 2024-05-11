package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Curso;
import org.junit.jupiter.api.Test;

import static com.pbcompass.cursos.common.CursoMapperConstantes.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CursoMapperTest {

    @Test
    public void toCurso_ComCursoCadastrarDto_RetornarCurso() {
        CursoCadastrarDto testeCursoDto = CURSO_CADASTRAR_DTO;

        Curso testeCurso = CursoMapper.toCurso(testeCursoDto);

        assertThat(testeCurso.getNome()).isEqualTo(testeCursoDto.getNome());
        assertThat(testeCurso.getQuantidadeHoras()).isEqualTo(testeCursoDto.getQuantidadeHoras());
        assertThat(testeCurso.getProfessor().getId()).isEqualTo(testeCursoDto.getProfessor().getId());
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
    }

    @Test
    public void toRespostaDto_RetornarCurso() {
        Curso testeCurso = CURSO;

        CursoRespostaDto testeCursoDto = CursoMapper.toRespostaDto(testeCurso);

        assertThat(testeCursoDto.getId()).isEqualTo(testeCurso.getId());
        assertThat(testeCursoDto.getNome()).isEqualTo(testeCurso.getNome());
        assertThat(testeCursoDto.getQuantidadeHoras()).isEqualTo(testeCurso.getQuantidadeHoras());
        assertThat(testeCursoDto.getProfessor().getId()).isEqualTo(testeCurso.getProfessor().getId());
        assertThat(testeCursoDto.isAtivo()).isEqualTo(testeCurso.isAtivo());
        assertThat(testeCursoDto.getArea()).isEqualTo(testeCurso.getArea());
    }

}
