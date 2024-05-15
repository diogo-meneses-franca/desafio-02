package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.dto.*;
import com.pbcompass.cursos.exceptions.customizadas.ErroComunicacaoEntreApisException;
import com.pbcompass.cursos.feignClients.AlunoFeign;
import com.pbcompass.cursos.dto.mapper.CursoMapper;
import com.pbcompass.cursos.entities.Aluno;
import com.pbcompass.cursos.entities.Curso;
import com.pbcompass.cursos.exceptions.MensagemErroPadrao;
import com.pbcompass.cursos.service.CursoService;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Tag(name = "Curso", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um Curso.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService service;
    private final AlunoFeign alunoFeign;

    @Operation(summary = "Cadastrar um novo curso",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "curso cadastrar com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))),
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
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRespostaDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Nenhum registro encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))),
            }
    )
    @GetMapping
    public ResponseEntity<List<BuscarTodosCursosRespostaDto>> buscarTodos() {
        List<Curso> lista = service.buscarTodos();
        return ResponseEntity.ok(CursoMapper.toListBuscarTodosCursosRespostaDto(lista));
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
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CursoRespostaDto> buscarPorId(@PathVariable Long id) {
        Curso curso = service.buscarPorId(id);
        Set<Aluno> alunos = curso.getAlunos();
        if (!alunos.isEmpty()){
            try{
                List<AlunoFeignBuscarTodosDto> alunosList = alunoFeign.buscarTodos().getBody();
                Set<AlunoRespostaDto> respostaDtos = new HashSet<>();
                for(AlunoFeignBuscarTodosDto alunoDto : alunosList) {
                    for(Aluno aluno : alunos) {
                        if(aluno.getId().equals(alunoDto.getId())) {
                            respostaDtos.add(new AlunoRespostaDto(alunoDto.getNome(), alunoDto.getSexo(), aluno.isAtivo()));
                        }
                    }
                }
                CursoRespostaDto cursoDto = CursoMapper.toRespostaDto(curso);
                cursoDto.setAlunos(respostaDtos);
                return ResponseEntity.ok().body(cursoDto);
            }catch (FeignException e){
                log.error(String.format("Erro ao buscar curso por id:  %s", e.getMessage()));
                throw new ErroComunicacaoEntreApisException("Falha ao buscar alunos matriculados");
            }
        }
        CursoRespostaDto cursoDto = CursoMapper.toRespostaDto(curso);
        return ResponseEntity.ok().body(cursoDto);
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
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class)))
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
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item a atualizar não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))),
            }
    )

    @PutMapping
    public ResponseEntity<CursoRespostaDto> alterar(@RequestBody CursoRespostaDto respostaDto) {
        Curso curso = CursoMapper.toCurso(respostaDto);
        CursoRespostaDto resposta = CursoMapper.toRespostaDto(service.alterar(curso));
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/matricular/{cursoId}")
    public ResponseEntity<CursoRespostaDto> matricular(@PathVariable Long cursoId, @RequestParam Long alunoId){
        Curso curso = service.matricular(cursoId, alunoId);
        CursoRespostaDto resposta = CursoMapper.toRespostaDto(curso);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/inativar-matricula/{cursoId}")
    public ResponseEntity<CursoRespostaDto> inativarMatricula(@PathVariable Long cursoId,@RequestParam Long alunoId){
        CursoRespostaDto respostaDto = CursoMapper.toRespostaDto(service.inativarMatricula(cursoId, alunoId));
        return ResponseEntity.ok().body(respostaDto);
    }

}
