package com.pbcompass.alunos.domain;

import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.exception.AlunoMatriculadoException;
import com.pbcompass.alunos.exception.CpfUnicoException;
import com.pbcompass.alunos.repository.AlunoRepository;
import com.pbcompass.alunos.service.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.pbcompass.alunos.common.AlunoConstantes.*;
import static com.pbcompass.alunos.common.CursoConstantes.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Test
    public void cadastrarAluno_ComDadosValidos_RetornarAluno() {
        when(alunoRepository.save(ALUNO)).thenReturn(ALUNO);
        Aluno testeAluno = alunoService.cadastrar(ALUNO);

        assertThat(testeAluno).isEqualTo(ALUNO);
    }

    @Test
    public void cadastrarAluno_ComDadosInvalidos_LancarExcecao() {
        when(alunoService.cadastrar(ALUNO_INVALIDO)).thenThrow(CpfUnicoException.class);

        assertThatThrownBy(() -> alunoService.cadastrar(ALUNO_INVALIDO)).isInstanceOf(CpfUnicoException.class);
    }

    @Test
    public void buscarAluno_ComIdExistente_RetornarAluno() {
        when(alunoRepository.findById(ALUNO.getId())).thenReturn(Optional.of(ALUNO));
        Aluno testeAluno = alunoService.buscarPorId(1L);

        assertThat(testeAluno).isEqualTo(ALUNO);
    }

    @Test
    public void buscarAluno_ComIdInexistente_LancarExcecao() {
        when(alunoRepository.findById(99L)).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> alunoService.buscarPorId(99L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void matricularAluno_EmCursoNaoMatriculado_RetornarAluno() {
        when(alunoRepository.findById(ALUNO.getId())).thenReturn(Optional.of(ALUNO));
        alunoService.matricular(ALUNO.getId(), CURSO_MATRICULAR_DTO);

        assertThat(ALUNO.getMatriculas().size()).isEqualTo(1);
        assertThat(ALUNO.getMatriculas().stream().findFirst().get().getCursoId()).isEqualTo(CURSO_MATRICULAR_DTO.getCursoId());
    }

    @Test
    public void inativarAluno_ComIdExistente_RetornarAluno() {
        when(alunoRepository.findById(ALUNO.getId())).thenReturn(Optional.of(ALUNO));
        alunoService.inativar(ALUNO.getId());

        assertThat(ALUNO.getAtivo()).isEqualTo(false);
    }

}
