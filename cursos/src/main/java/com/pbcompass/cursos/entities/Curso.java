package com.pbcompass.cursos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cursos")
@Entity
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome",unique = true, nullable = false, length = 100)
    private String nome;

    @Column(name = "quantidadeHoras", nullable = false)
    private Integer quantidadeHoras;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "area", length = 25)
    private Area area;

    private Integer totalAlunos = 0;

    @JoinColumn(name = "curso_aluno")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Aluno> alunos = new HashSet<>();

    public enum Area{
        CIENCIAS_HUMANAS,
        CIENCIAS_SOCIAIS,
        TECNOLOGIAS
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
