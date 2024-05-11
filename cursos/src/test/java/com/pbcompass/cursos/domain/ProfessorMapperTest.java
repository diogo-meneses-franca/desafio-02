package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.dto.mapper.ProfessorMapper;
import com.pbcompass.cursos.entities.Professor;
import org.junit.jupiter.api.Test;

import static com.pbcompass.cursos.common.ProfessorMapperConstantes.PROFESSOR_CADASTRAR_DTO;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfessorMapperTest {

    @Test
    public void toProfessor_RetornarProfessor() {
        Professor testeProf = ProfessorMapper.toProfessor(PROFESSOR_CADASTRAR_DTO);

        assertThat(testeProf.getNome()).isEqualTo(PROFESSOR_CADASTRAR_DTO.getNome());
    }

}
