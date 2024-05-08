package com.pbcompass.cursos.service;

import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.EntityNotFoundException;
import com.pbcompass.cursos.repository.CursoRepository;
import com.pbcompass.cursos.repository.ProfessorRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Professor addProfessor(Professor professor){
        try{
            return professorRepository.save(professor);
        } catch (PersistenceException e) {
            throw new PersistenceException("Erro ao salvar dados.");
        }
    }

    @Transactional
    public Professor findById(Long id) {
        return professorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Professor com 'id='%s''nao foi encontrado", id)));
    }

    @Transactional
    public Professor findByName(String nome) {
        return professorRepository.findByNome(nome).orElseThrow(
                () -> new EntityNotFoundException(String.format("Professor '%s' não foi encontrado", nome)));
    }
}
