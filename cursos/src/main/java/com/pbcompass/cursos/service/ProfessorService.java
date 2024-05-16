package com.pbcompass.cursos.service;

import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.customizadas.DadosDeCadastroInvalidosException;
import com.pbcompass.cursos.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Transactional
    public Professor cadastrar(Professor professor){
        try {
            return professorRepository.save(professor);
        }catch (DataIntegrityViolationException e){
            throw new DadosDeCadastroInvalidosException("Nome do professor não pode ser null");
        }
    }

    @Transactional(readOnly = true)
    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Professor com 'id='%s''nao foi encontrado", id)));
    }

    @Transactional(readOnly = true)
    public Professor buscarPorNome(String nome) {
        return professorRepository.findByNome(nome).orElseThrow(
                () -> new EntityNotFoundException(String.format("Professor '%s' não foi encontrado", nome)));
    }

    @Transactional
    public Professor alterar(Professor professor) {
        return professorRepository.saveAndFlush(professor);
    }

    @Transactional(readOnly = true)
    public List<Professor> buscarTodos() {
        List<Professor> professores = professorRepository.findAll();
        if (professores.isEmpty()) {
            throw new EntityNotFoundException("Nenhum professor encontrado.");
        }
        return professores;
    }
}


