package com.pbcompass.cursos.controller;

import com.pbcompass.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService service;


}
