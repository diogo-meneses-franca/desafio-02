package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Curso;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CursoMapperTest {

    @Test
    public void toCurso_ComCursoCadastrarDto_RetornarCurso() {
        CursoCadastrarDto testeCursoDto =
                new CursoCadastrarDto("Marcos", 80,
                        new ProfessorRespostaDto(1l, "PROFESSOR"), Curso.Area.TECNOLOGIAS);

        Curso testeCurso = CursoMapper.toCurso(testeCursoDto);

        assertThat(testeCurso.getNome()).isEqualTo(testeCursoDto.getNome());
        assertThat(testeCurso.getQuantidadeHoras()).isEqualTo(testeCursoDto.getQuantidadeHoras());
        assertThat(testeCurso.getProfessor().getId()).isEqualTo(testeCursoDto.getProfessor().getId());
        assertThat(testeCurso.getArea()).isEqualTo(testeCursoDto.getArea());
    }

    @Test
    public void toCurso_ComCursoRespostaDto_RetornarCurso() {
        CursoRespostaDto testeCursoDto =
                new CursoRespostaDto(1L, "Marcos", 80,
                        new ProfessorRespostaDto(1l, "PROFESSOR"), true,  Curso.Area.TECNOLOGIAS);

        Curso testeCurso = CursoMapper.toCurso(testeCursoDto);

        assertThat(testeCurso.getId()).isEqualTo(testeCursoDto.getId());
        assertThat(testeCurso.getNome()).isEqualTo(testeCursoDto.getNome());
        assertThat(testeCurso.getQuantidadeHoras()).isEqualTo(testeCursoDto.getQuantidadeHoras());
        assertThat(testeCurso.getProfessor().getId()).isEqualTo(testeCursoDto.getProfessor().getId());
        assertThat(testeCurso.isAtivo()).isEqualTo(testeCursoDto.isAtivo());
        assertThat(testeCurso.getArea()).isEqualTo(testeCursoDto.getArea());
    }

}
