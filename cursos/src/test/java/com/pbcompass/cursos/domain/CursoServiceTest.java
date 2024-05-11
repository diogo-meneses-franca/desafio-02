package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.exceptions.customizadas.EntityNotFoundException;
import com.pbcompass.cursos.exceptions.customizadas.PersistenceException;
import com.pbcompass.cursos.repository.CursoRepository;
import com.pbcompass.cursos.service.CursoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.pbcompass.cursos.common.CursoConstantes.CURSO;
import static com.pbcompass.cursos.common.CursoConstantes.CURSO_INVALIDO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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
        when(cursoRepository.save(CURSO_INVALIDO)).thenThrow(PersistenceException.class);

        assertThatThrownBy(() -> cursoService.cadastrar(CURSO)).isInstanceOf(PersistenceException.class);
    }

    @Test
    public void buscarCurso_ComIdExistente_RetornarCurso() {
        when(cursoRepository.findById(CURSO.getId())).thenReturn(Optional.of(CURSO));
        Curso testeCurso = cursoService.buscarPorId(CURSO.getId());

        assertThat(testeCurso).isEqualTo(CURSO);
    }

    @Test
    public void buscarCurso_ComIdInexistente_LancarExcecao() {
        when(cursoRepository.findById(any())).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> cursoService.buscarPorId(99L)).isInstanceOf(EntityNotFoundException.class);
    }

}
