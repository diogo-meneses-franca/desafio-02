package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.exceptions.ErrorMessage;
import com.pbcompass.cursos.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Curso", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um Curso.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService service;

    @Operation(summary = "Cadastrar um novo curso",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "curso cadastrar com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @PostMapping
    public ResponseEntity<CursoRespostaDto> cadastrar(@RequestBody CursoCadastrarDto dto) {
        Curso curso = CursoMapper.toCurso(dto);
        CursoRespostaDto respostaDto = CursoMapper.toRespostaDto(service.cadastrar(curso));
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    @Operation(summary = "buscar todos os curso",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recursos recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<CursoRespostaDto>> buscarTodos() {
        List<Curso> lista = service.buscarTodos();
        return ResponseEntity.ok(CursoMapper.toListaDto(lista));
    }

    @Operation(summary = "buscar curso por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CursoRespostaDto> buscarId(@PathVariable long id) {
        Curso curso = service.buscarPorId(id);
        return ResponseEntity.ok().body(CursoMapper.toRespostaDto(curso));
    }


    @Operation(summary = "buscar curso por nome",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/nome/{nome}")
    public ResponseEntity<CursoRespostaDto> buscarPorNome(@PathVariable String nome) {
        Curso curso = service.buscarPorNome(nome);
        return ResponseEntity.ok().body(CursoMapper.toRespostaDto(curso));
    }

    @Operation(summary = "altera qualquer item no curso ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso alterado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Corpo requisição invalido",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item a atualizar não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @PutMapping
    public ResponseEntity<CursoRespostaDto> alterar(@RequestBody CursoRespostaDto respostaDto) {
        Curso curso = CursoMapper.toCurso(respostaDto);
        CursoRespostaDto resposta = CursoMapper.toRespostaDto(service.alterar(curso));
        return ResponseEntity.ok(resposta);
    }

}
