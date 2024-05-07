package com.pbcompass.cursos.repository;

import com.pbcompass.cursos.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByName(String nome);
}