package com.pbcompass.cursos.domain;

import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.customizadas.DadosDeCadastroInvalidosException;
import com.pbcompass.cursos.repository.ProfessorRepository;
import com.pbcompass.cursos.service.ProfessorService;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static com.pbcompass.cursos.common.ProfessorConstantes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Test
    public void cadastrarProfessor_ComDadosValidos_RetornarProfessor() {
        when(professorRepository.save(PROFESSOR)).thenReturn(PROFESSOR);
        Professor testeProf = professorService.cadastrar(PROFESSOR);

        assertThat(testeProf).isEqualTo(PROFESSOR);
    }

    @Test
    public void cadastrarProfessor_ComDadosInvalidos_LancarExcecao() {
        when(professorRepository.save(PROFESSOR_INVALIDO)).thenThrow(DadosDeCadastroInvalidosException.class);

        assertThatThrownBy(() -> professorService.cadastrar(PROFESSOR_INVALIDO)).isInstanceOf(DadosDeCadastroInvalidosException.class);
    }



    /*
    @Test
    public void buscarProfessor_ComIdExistente_RetornarProfessor() {
        when(professorRepository.findById(PROFESSOR.getId())).thenReturn(Optional.of(PROFESSOR));
        Professor testeProf = professorService.buscarPorId(PROFESSOR.getId());

        assertThat(testeProf).isEqualTo(PROFESSOR);
    }

    @Test
    public void buscarProfessor_ComIdInexistente_LancarExcecao() {
        when(professorRepository.findById(any())).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> professorService.buscarPorId(99L)).isInstanceOf(EntityNotFoundException.class);
    }


    @Test
    public void buscarProfessor_ComNomeExistente_RetornarProfessor() {
        when(professorRepository.findByNome(PROFESSOR.getNome())).thenReturn(Optional.of(PROFESSOR));
        Professor testeProf = professorService.buscarPorNome(PROFESSOR.getNome());

        assertThat(testeProf).isEqualTo(PROFESSOR);
    }

    @Test
    public void buscarProfessor_ComNomeInexistente_LancarExcecao() {
        when(professorRepository.findByNome(any())).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> professorService.buscarPorNome("PROFESSOR_INEXISTENTE")).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void buscarTodosOsProfessores_SemParametros_RetornarListaDeProfessores() {
        when(professorRepository.findAll()).thenReturn(PROF_LIST);

        assertThat(professorService.buscarTodos()).isEqualTo(PROF_LIST);
    }
    */

}
