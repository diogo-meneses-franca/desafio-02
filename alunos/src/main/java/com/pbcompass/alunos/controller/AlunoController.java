package com.pbcompass.alunos.controller;

import com.pbcompass.alunos.dto.AlunoCriarDto;
import com.pbcompass.alunos.dto.AlunoRespostaDto;
import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.mapper.AlunoMapper;
import com.pbcompass.alunos.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoRespostaDto> cadastrar(@RequestBody @Valid AlunoCriarDto dto) {
        Aluno aluno = AlunoMapper.toAluno(dto);
        AlunoRespostaDto resposta = AlunoMapper.toRespostaDto(alunoService.cadastrar(aluno));
        return ResponseEntity.ok(resposta);
    }

}
