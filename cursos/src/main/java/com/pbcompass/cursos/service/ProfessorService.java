package com.pbcompass.cursos.service;

import com.pbcompass.cursos.dto.ProfessorCadastrarDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.EntityNotFoundException;
import com.pbcompass.cursos.repository.CursoRepository;
import com.pbcompass.cursos.repository.ProfessorRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;

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

    @Transactional
    public ProfessorRespostaDto alterar(Long id, ProfessorCadastrarDto dto) {
        Curso curso = cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Professor não está associado a nenhum curso"));
        Professor professor = professorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Professor não encontrado"));
        professor.setNome(dto.getNome());
        professorRepository.save(professor);
        return new ProfessorRespostaDto(id, professor.getNome());
    }

    @Transactional(readOnly = true)
    public List<Professor> buscarTodos(){
        return professorRepository.findAll();
    }
}


