package com.pbcompass.alunos.service;

import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.exception.CpfUniqueViolationException;
import com.pbcompass.alunos.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional
    public Aluno cadastrar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(String.format("CPF '%s' já cadastrado", aluno.getCpf()));
        }
    }

}
