package com.pbcompass.cursos.service;

import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.EntityNotFoundException;
import com.pbcompass.cursos.repository.ProfessorRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Transactional
    public Professor cadastrar(Professor professor){
        try{
            return professorRepository.save(professor);
        } catch (PersistenceException e) {
            throw new PersistenceException("Erro ao salvar dados.");
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
}
