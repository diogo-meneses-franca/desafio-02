package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.customizadas.NomeDoCursoUnicoException;
import com.pbcompass.cursos.repository.CursoRepository;
import com.pbcompass.cursos.service.CursoService;
import com.pbcompass.cursos.service.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.pbcompass.cursos.common.CursoConstantes.*;
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

    @Mock
    private ProfessorService professorService;

    @Test
    public void cadastrarCurso_ComDadosValidos_RetornarCurso() {
        when(cursoRepository.save(CURSO)).thenReturn(CURSO);
        Curso testeCurso = cursoService.cadastrar(CURSO);

        assertThat(testeCurso).isEqualTo(CURSO);
    }

    @Test
    public void cadastrarCurso_ComNomeRepetido_LancarExcecao() {
        cursoRepository.save(CURSO);
        CURSO_INVALIDO.setProfessor(new Professor(99L, "INVALIDO"));
        CURSO_INVALIDO.setNome("TADS");

        when(cursoRepository.save(CURSO_INVALIDO)).thenThrow(NomeDoCursoUnicoException.class);

        assertThatThrownBy(() -> cursoService.cadastrar(CURSO_INVALIDO)).isInstanceOf(NomeDoCursoUnicoException.class);
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

    @Test
    public void buscarCurso_ComNomeExistente_RetornarCurso() {
        when(cursoRepository.findByNome(CURSO.getNome())).thenReturn(Optional.of(CURSO));
        Curso testeCurso = cursoService.buscarPorNome(CURSO.getNome());

        assertThat(testeCurso).isEqualTo(CURSO);
    }

    @Test
    public void buscarCurso_ComNomeInexistente_LancarExcecao() {
        when(cursoRepository.findByNome(CURSO_INVALIDO.getNome())).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> cursoService.buscarPorNome(CURSO_INVALIDO.getNome())).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void buscarTodosOsCursos_SemParametros_RetornarListaDeCursos() {
        when(cursoRepository.findAll()).thenReturn(LIST_CURSO);

        assertThat(cursoService.buscarTodos()).isEqualTo(LIST_CURSO);
    }

    @Test
    public void alterarCurso_ComDadosValidos_RetornarCurso() {
        when(cursoRepository.saveAndFlush(CURSO)).thenReturn(CURSO);
        Curso testeCurso = cursoService.alterar(CURSO);

        assertThat(testeCurso).isEqualTo(CURSO);
    }

}
