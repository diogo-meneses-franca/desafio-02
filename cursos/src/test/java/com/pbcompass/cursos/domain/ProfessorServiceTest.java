package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.customizadas.PersistenceException;
import com.pbcompass.cursos.repository.ProfessorRepository;
import com.pbcompass.cursos.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.pbcompass.cursos.common.ProfessorConstantes.PROFESSOR;
import static com.pbcompass.cursos.common.ProfessorConstantes.PROFESSOR_INVALIDO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Test
    public void cadastrarCurso_ComDadosValidos_RetornarCurso() {
        when(professorRepository.save(PROFESSOR)).thenReturn(PROFESSOR);
        Professor testeProf = professorService.cadastrar(PROFESSOR);

        assertThat(testeProf).isEqualTo(PROFESSOR);
    }

    @Test
    public void cadastrarCurso_ComDadosInvalidos_LancarExcecao() {
        when(professorRepository.save(PROFESSOR_INVALIDO)).thenThrow(PersistenceException.class);

        assertThatThrownBy(() -> professorService.cadastrar(PROFESSOR_INVALIDO)).isInstanceOf(PersistenceException.class);
    }

}
