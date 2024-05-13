package com.pbcompass.cursos.service;

import com.pbcompass.cursos.dto.AlunoDto;
import com.pbcompass.cursos.entities.Aluno;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.exceptions.customizadas.CursoInativoException;
import com.pbcompass.cursos.exceptions.customizadas.LimiteMatriculasAtingidoException;
import com.pbcompass.cursos.exceptions.customizadas.AlunoMatriculadoException;
import com.pbcompass.cursos.exceptions.customizadas.NomeDoCursoUnicoException;
import com.pbcompass.cursos.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ProfessorService professorService;

    @Transactional
    public Curso cadastrar(Curso curso){
        try {
            curso.setProfessor(professorService.buscarPorId(curso.getProfessor().getId()));
            return cursoRepository.save(curso);

        }catch (DataIntegrityViolationException e){
            throw new NomeDoCursoUnicoException(String.format("Curso com o nome %s já cadastrado", curso.getNome()));
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
        List<Curso> curso = cursoRepository.findAll();
        return curso;
    }

    @Transactional
    public Curso alterar(Curso curso) {
        return cursoRepository.saveAndFlush(curso);
    }

    @Transactional
    public Curso matricular(Long cursoId, AlunoDto dto) {
        Curso curso = buscarPorId(cursoId);
        if(!curso.isAtivo()){
            throw new CursoInativoException("Este curso encontra-se inativo");
        }
        if (curso.getAlunos().size() >= 10){
            throw new LimiteMatriculasAtingidoException("Não há mais vagas disponíveis neste curso");
        }
        curso.getAlunos().forEach(obj -> {
            if(obj.getAlunoId().equals(dto.getAlunoId())){
                throw new AlunoMatriculadoException("Aluno ja está matriculado neste curso");
            }
        });
        Aluno aluno = new Aluno();
        aluno.setAlunoId(dto.getAlunoId());
        aluno.setCurso(curso);
        aluno.setAtivo(true);
        curso.getAlunos().add(aluno);
        curso.setTotalAlunos(curso.getTotalAlunos() + 1);
        return cursoRepository.saveAndFlush(curso);
    }

    @Transactional
    public Curso inativarMatricula(Long cursoId, AlunoDto dto){
        Curso curso = buscarPorId(cursoId);
        boolean contemAluno = false;
        for(Aluno aluno : curso.getAlunos()){
            if(aluno.getAlunoId().equals(dto.getAlunoId())){
                contemAluno = true;
            }
        }
        if(contemAluno){
            Set<Aluno> alunos = curso.getAlunos().stream().peek(aluno -> {
                if(aluno.getAlunoId().equals(dto.getAlunoId())){
                    aluno.setAtivo(false);
                }
            }).collect(Collectors.toSet());
            curso.setAlunos(alunos);
            return cursoRepository.save(curso);
        }
        throw new EntityNotFoundException("Aluno não matriculado neste curso");
    }
}
