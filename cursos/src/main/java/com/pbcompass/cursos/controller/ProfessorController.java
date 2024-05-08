package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.ProfessorCriarDto;
import com.pbcompass.cursos.dto.ProfessorRespotaDto;
import com.pbcompass.cursos.dto.mapper.ProfessorMapper;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<ProfessorRespotaDto> cadastrar(@RequestBody ProfessorCriarDto dto) {
        Professor professor = ProfessorMapper.toProfessor(dto);
        ProfessorRespotaDto respostaDto = ProfessorMapper.toRespostaDto(service.cadastrar(professor));
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }
}
