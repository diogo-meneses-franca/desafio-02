package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.dto.mapper.ProfessorMapper;
import com.pbcompass.cursos.entities.Professor;
import org.junit.jupiter.api.Test;

import static com.pbcompass.cursos.common.ProfessorMapperConstantes.PROFESSOR;
import static com.pbcompass.cursos.common.ProfessorMapperConstantes.PROFESSOR_CADASTRAR_DTO;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfessorMapperTest {

    @Test
    public void toProfessor_RetornarProfessor() {
        Professor testeProf = ProfessorMapper.toProfessor(PROFESSOR_CADASTRAR_DTO);

        assertThat(testeProf.getNome()).isEqualTo(PROFESSOR_CADASTRAR_DTO.getNome());
    }

    @Test
    public void toProfessorRespostaDto_RetornarProfessorRespostaDto() {
        ProfessorRespostaDto testeProfDto = ProfessorMapper.toRespostaDto(PROFESSOR);

        assertThat(testeProfDto.getId()).isEqualTo(PROFESSOR.getId());
        assertThat(testeProfDto.getNome()).isEqualTo(PROFESSOR.getNome());
    }

}
