package com.pbcompass.alunos.service;

import com.pbcompass.alunos.dto.CursoMatricularDto;
import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.entity.Curso;
import com.pbcompass.alunos.exception.CpfUnicoException;
import com.pbcompass.alunos.exception.AlunoMatriculadoException;
import com.pbcompass.alunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional
    public Aluno cadastrar(Aluno aluno) {
        try {
            aluno.setAtivo(true);
            return alunoRepository.save(aluno);
        } catch (DataIntegrityViolationException e) {
            throw new CpfUnicoException(String.format("CPF '%s' já cadastrado", aluno.getCpf()));
        }
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Aluno com id %s não encontrado", id))
        );
    }

    @Transactional
    public Aluno matricular(Long alunoId, CursoMatricularDto dto) {
        Aluno aluno = buscarPorId(alunoId);
        Curso matricula = new Curso();
        matricula.setCursoId(dto.getCursoId());
        aluno.getMatriculas().forEach(obj -> {
            if (obj.getCursoId().equals(dto.getCursoId())){
                throw new AlunoMatriculadoException("Aluno ja está matriculado neste curso");}
        });
        aluno.getMatriculas().add(matricula);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno inativar(Long id) {
        Aluno aluno = buscarPorId(id);
        aluno.setAtivo(false);
        return alunoRepository.save(aluno);
    }
}
