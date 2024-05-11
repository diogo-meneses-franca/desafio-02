package com.pbcompass.alunos.service;

import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.exception.CpfUniqueViolationException;
import com.pbcompass.alunos.repository.AlunoRepository;
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
            throw new CpfUniqueViolationException(String.format("CPF '%s' já cadastrado", aluno.getCpf()));
        }
    }

}
