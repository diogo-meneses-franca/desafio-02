package com.pbcompass.alunos.controller;

import com.pbcompass.alunos.dto.AlunoCadastrarDto;
import com.pbcompass.alunos.dto.AlunoRespostaDto;
import com.pbcompass.alunos.entity.Aluno;
import com.pbcompass.alunos.exception.MensagemDeErroPadrao;
import com.pbcompass.alunos.mapper.AlunoMapper;
import com.pbcompass.alunos.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Cadastrar um novo aluno",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunoRespostaDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "CPF já cadastrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemDeErroPadrao.class))
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemDeErroPadrao.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<AlunoRespostaDto> cadastrar(@RequestBody @Valid AlunoCadastrarDto dto) {
        Aluno aluno = AlunoMapper.toAluno(dto);
        AlunoRespostaDto resposta = AlunoMapper.toRespostaDto(alunoService.cadastrar(aluno));
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

}
