package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.exceptions.customizadas.PersistenceException;
import com.pbcompass.cursos.repository.CursoRepository;
import com.pbcompass.cursos.service.CursoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.pbcompass.cursos.common.CursoConstantes.CURSO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    public void cadastrarCurso_ComDadosValidos_RetornarCurso() {
        when(cursoRepository.save(CURSO)).thenReturn(CURSO);
        Curso testeCurso = cursoService.cadastrar(CURSO);

        assertThat(testeCurso).isEqualTo(CURSO);
    }

    @Test
    public void cadastrarCurso_ComDadosInvalidos_LancarExcecao() {
        when(cursoRepository.save(CURSO)).thenThrow(PersistenceException.class);

        assertThatThrownBy(() -> cursoService.cadastrar(CURSO)).isInstanceOf(PersistenceException.class);
    }

}
