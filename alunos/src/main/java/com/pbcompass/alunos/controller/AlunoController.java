package com.pbcompass.alunos.controller;

import com.pbcompass.alunos.dto.*;
import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.exception.ErroInativarMatriculaException;
import com.pbcompass.alunos.exception.ErroMatricularAlunoException;
import com.pbcompass.alunos.exception.MensagemErroPadrao;
import com.pbcompass.alunos.feignClients.CursoFeign;
import com.pbcompass.alunos.mapper.AlunoMapper;
import com.pbcompass.alunos.service.AlunoService;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "Alunos/Matrículas", description = "Contém todas as operações relativas ao cadastro, matrícula e consulta de alunos")
@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;
    private final CursoFeign cursoFeign;

    @Operation(summary = "Cadastrar um novo aluno",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunoRespostaDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "CPF já cadastrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<AlunoRespostaDto> cadastrar(@RequestBody @Valid AlunoCadastrarDto dto) {
        Aluno aluno = AlunoMapper.toAluno(dto);
        AlunoRespostaDto resposta = AlunoMapper.toRespostaDto(alunoService.cadastrar(aluno));
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Operation(summary = "Buscar um aluno por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunoRespostaDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ID do aluno não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemErroPadrao.class))
                    ),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AlunoRespostaDto> buscarPorId(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        AlunoRespostaDto dto = AlunoMapper.toRespostaDto(aluno);
        Set<CursoRespostaDto> cursos = dto.getMatriculas().stream().map(curso -> cursoFeign.buscarPorId(curso.getId()).getBody()).collect(Collectors.toSet());
        dto.setMatriculas(cursos);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<AlunoRespostaDto> inativar(@PathVariable Long id){
        AlunoRespostaDto aluno = AlunoMapper.toRespostaDto(alunoService.inativar(id));
        try {
            aluno.getMatriculas().forEach(curso -> {
                cursoFeign.inativarMatricula(curso.getId(), new AlunoMatricularDto(aluno.getId()));
            });
            Set<CursoRespostaDto> cursos = aluno.getMatriculas().stream().map(curso ->
                    cursoFeign.buscarPorId(curso.getId()).getBody()).collect(Collectors.toSet());
            aluno.setMatriculas(cursos);
        }catch (FeignException e){
            throw new ErroInativarMatriculaException(e.getMessage());
        }
        return ResponseEntity.ok().body(aluno);
    }

    @PutMapping("/matricular/{alunoId}")
    public ResponseEntity<AlunoRespostaDto> matricular(@PathVariable Long alunoId, @RequestBody CursoMatricularDto dto) {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        if (aluno.getAtivo()) {
            try {
                AlunoMatricularDto matriculaDto = new AlunoMatricularDto(alunoId);
                HttpStatusCode status = cursoFeign.matricular(dto.getCursoId(), matriculaDto).getStatusCode();
                if (status == HttpStatus.OK) {
                    Aluno matricular = alunoService.matricular(alunoId, dto);
                    AlunoRespostaDto resposta = AlunoMapper.toRespostaDto(matricular);
                    Set<CursoRespostaDto> cursos = resposta.getMatriculas().stream().map(curso -> cursoFeign.buscarPorId(curso.getId()).getBody()).collect(Collectors.toSet());
                    resposta.setMatriculas(cursos);
                    return ResponseEntity.status(HttpStatus.OK).body(resposta);
                }
            } catch (FeignException e) {
                throw new ErroMatricularAlunoException(e.getMessage());
            }
        }
        throw new ErroMatricularAlunoException("Alunos inativos não podem realizar matriculas");
    }
}
