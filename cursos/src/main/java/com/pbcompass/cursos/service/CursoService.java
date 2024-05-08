package com.pbcompass.cursos.service;

import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.EntityNotFoundException;

import com.pbcompass.cursos.repository.CursoRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Curso criarCurso(Curso curso){
        try{
            return cursoRepository.save(curso);
        } catch (PersistenceException e) {
            throw new PersistenceException("Erro ao salvar curso.");
        }
    }

    @Transactional
    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso com 'id='%s''nao foi encontrado", id)));
    }

    @Transactional
    public Curso findByName(String nome) {
        return cursoRepository.findByNome(nome).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso '%s' não foi encontrado", nome)));
    }

    @Transactional
    public void inativarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
        curso.setAtivo(false);
        cursoRepository.save(curso);
    }

    @Transactional
    public void alterarProfessor(Long id, Professor prof) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
        curso.setProfessor(new Professor(prof.getId(), prof.getNome()));
        cursoRepository.save(curso);
    }
}
