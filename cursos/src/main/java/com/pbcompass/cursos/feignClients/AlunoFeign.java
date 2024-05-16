package com.pbcompass.cursos.feignClients;

import com.pbcompass.cursos.dto.AlunoFeignBuscarTodosDto;
import com.pbcompass.cursos.dto.AlunoRespostaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "alunos", url = "http://localhost:8000")
public interface AlunoFeign {

    @GetMapping("/api/alunos/{alunoId}")
    ResponseEntity<AlunoRespostaDto> buscarPorId(@PathVariable("alunoId") Long id);

    @GetMapping("/api/alunos")
    ResponseEntity<List<AlunoFeignBuscarTodosDto>> buscarTodos();
}
