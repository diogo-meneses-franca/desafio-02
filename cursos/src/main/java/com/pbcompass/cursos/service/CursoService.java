package com.pbcompass.cursos.service;

import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.exceptions.customizadas.EntityNotFoundException;

import com.pbcompass.cursos.repository.CursoRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public Curso cadastrar(Curso curso){
        try{
            return cursoRepository.save(curso);
        } catch (PersistenceException e) {
            throw new PersistenceException("Erro ao salvar curso.");
        }
    }

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso com 'id='%s''nao foi encontrado", id)));
    }

    @Transactional(readOnly = true)
    public Curso buscarPorNome(String nome) {
        return cursoRepository.findByNome(nome).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso '%s' não foi encontrado", nome)));
    }

    @Transactional
    public void inativarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
        curso.setAtivo(false);
        cursoRepository.save(curso);
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }
}
