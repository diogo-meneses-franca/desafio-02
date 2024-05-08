package com.pbcompass.cursos.dto.mapper;

import com.pbcompass.cursos.dto.CursoCriarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.entities.Curso;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CursoMapper {

    public static Curso toCurso(CursoCriarDto dto) {
        return new ModelMapper().map(dto, Curso.class);
    }

    public static CursoRespostaDto toRespostaDto(Curso curso) {
        return new ModelMapper().map(curso, CursoRespostaDto.class);
    }
}
