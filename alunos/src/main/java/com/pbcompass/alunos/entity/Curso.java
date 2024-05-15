package com.pbcompass.alunos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "curso")
public class Curso {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    private Long cursoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

}
