package com.pbcompass.alunos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Aluno implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "sexo", length = 1)
    private String sexo;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "aluno_curso")
    private Set<Curso> matriculas = new HashSet<>();

    @Column(name = "ativo")
    private Boolean ativo;

    public Aluno(String nome, String cpf, LocalDate dataNascimento, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }
}
