package com.pbcompass.cursos.dto.mapper;

import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.entities.Curso;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CursoMapper {

    public static Curso toCurso(CursoCadastrarDto dto) {
        return new ModelMapper().map(dto, Curso.class);
    }

    public static CursoRespostaDto toRespostaDto(Curso curso) {
        return new ModelMapper().map(curso, CursoRespostaDto.class);
    }

    public static List<CursoRespostaDto> toListaDto(List<Curso> cursoList) {
        return cursoList.stream()
                .map(CursoMapper::toRespostaDto)
                .collect(Collectors.toList());
    }
}
