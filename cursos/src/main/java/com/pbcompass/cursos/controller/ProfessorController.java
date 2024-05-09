package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.ProfessorCadastrarDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.dto.mapper.ProfessorMapper;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<ProfessorRespostaDto> cadastrar(@RequestBody ProfessorCadastrarDto dto) {
        Professor professor = ProfessorMapper.toProfessor(dto);
        ProfessorRespostaDto respostaDto = ProfessorMapper.toRespostaDto(service.cadastrar(professor));
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ProfessorRespostaDto> alterar(@PathVariable Long id, @RequestBody ProfessorCadastrarDto dto) {
        ProfessorRespostaDto respostaDto = service.alterar(id, dto);
        return ResponseEntity.ok(respostaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDto> buscarPorId(@PathVariable Long id) {
        Professor professor = service.buscarPorId(id);
        return ResponseEntity.ok().body(ProfessorMapper.toRespostaDto(professor));
    }

    @GetMapping
    public ResponseEntity<ProfessorRespostaDto> buscarPorNome(@RequestParam String nome) {
        Professor professor = service.buscarPorNome(nome);
        return ResponseEntity.ok().body(ProfessorMapper.toRespostaDto(professor));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<ProfessorRespostaDto>> buscarTodos() {
        List<Professor> lista = service.buscarTodos();
        return ResponseEntity.ok(ProfessorMapper.toListaDto(lista));
    }
}
