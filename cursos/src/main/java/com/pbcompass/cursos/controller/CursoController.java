package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.CursoCriarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService service;

    @PostMapping
    public ResponseEntity<CursoRespostaDto> cadastrar(@RequestBody CursoCriarDto dto){
        Curso curso = CursoMapper.toCurso(dto);
        CursoRespostaDto respostaDto = CursoMapper.toRespostaDto(service.cadastrar(curso));
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }
}
