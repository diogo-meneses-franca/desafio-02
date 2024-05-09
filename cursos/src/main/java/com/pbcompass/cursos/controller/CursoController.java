package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService service;

    @PostMapping
    public ResponseEntity<CursoRespostaDto> cadastrar(@RequestBody CursoCadastrarDto dto) {
        Curso curso = CursoMapper.toCurso(dto);
        CursoRespostaDto respostaDto = CursoMapper.toRespostaDto(service.cadastrar(curso));
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<CursoRespostaDto>> buscarTodos() {
        List<Curso> lista = service.buscarTodos();
        return ResponseEntity.ok(CursoMapper.toListaDto(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoRespostaDto> buscarId(@PathVariable long id) {
        Curso curso = service.buscarPorId(id);
        return ResponseEntity.ok().body(CursoMapper.toRespostaDto(curso));
    }

    @GetMapping
    public ResponseEntity<CursoRespostaDto> buscarPorNome(@RequestParam String nome) {
        Curso curso = service.buscarPorNome(nome);
        return ResponseEntity.ok().body(CursoMapper.toRespostaDto(curso));
    }
}
