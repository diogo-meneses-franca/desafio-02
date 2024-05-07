package com.pbcompass.cursos.repository;

import com.pbcompass.cursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByName(String nome);
}