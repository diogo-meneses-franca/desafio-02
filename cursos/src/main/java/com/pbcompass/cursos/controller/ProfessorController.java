package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.ProfessorCadastrarDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.dto.mapper.ProfessorMapper;
import com.pbcompass.cursos.entities.Professor;
import com.pbcompass.cursos.exceptions.ErrorMessage;
import com.pbcompass.cursos.service.ProfessorService;
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

@Tag(name = "Professor", description = "Contém todas as operações relativas aos recursos para cadastro e leitura de um Professor.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService service;

    @Operation(summary = "Cadastrar um novo Professor",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Professor cadastrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfessorRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @PostMapping
    public ResponseEntity<ProfessorRespostaDto> cadastrar(@RequestBody ProfessorCadastrarDto dto) {
        Professor professor = ProfessorMapper.toProfessor(dto);
        ProfessorRespostaDto respostaDto = ProfessorMapper.toRespostaDto(service.cadastrar(professor));
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDto);
    }

    @Operation(summary = "buscar Professor por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfessorRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDto> buscarPorId(@PathVariable Long id) {
        Professor professor = service.buscarPorId(id);
        return ResponseEntity.ok().body(ProfessorMapper.toRespostaDto(professor));
    }

    @Operation(summary = "buscar Professor por nome",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfessorRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProfessorRespostaDto> buscarPorNome(@PathVariable String nome) {
        Professor professor = service.buscarPorNome(nome);
        return ResponseEntity.ok().body(ProfessorMapper.toRespostaDto(professor));
    }

    @Operation(summary = "altera qualquer item no professor ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso alterado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfessorRespostaDto.class))),
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
    public ResponseEntity<ProfessorRespostaDto> alterar(@RequestBody ProfessorCadastrarDto respostaDto) {
        Professor professor = ProfessorMapper.toProfessor(respostaDto);
        ProfessorRespostaDto resposta = ProfessorMapper.toRespostaDto(service.alterar(professor));
        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "buscar todos os professores",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recursos recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfessorRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Nenhum registro encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @GetMapping
    public ResponseEntity<List<ProfessorRespostaDto>> buscarTodos() {
        List<Professor> lista = service.buscarTodos();
        return ResponseEntity.ok(ProfessorMapper.toListaDto(lista));
    }
}
