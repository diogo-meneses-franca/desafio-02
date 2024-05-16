package com.pbcompass.alunos.feignClients;

import com.pbcompass.alunos.dto.CursoRespostaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cursos", url = "http://localhost:8100")
public interface CursoFeign {

    @GetMapping("/api/cursos/{id}")
    ResponseEntity<CursoRespostaDto> buscarPorId(@PathVariable("id") Long id);

    @PutMapping("/api/cursos/matricular/{cursoId}")
    ResponseEntity<CursoRespostaDto> matricular(@PathVariable("cursoId") Long cursoId, @RequestParam Long alunoId);

    @PutMapping("/api/cursos/inativar-matricula/{cursoId}")
    ResponseEntity<CursoRespostaDto> inativarMatricula(@PathVariable("cursoId") Long cursoId, @RequestParam Long alunoId);
}
