package com.pbcompass.cursos.dto.mapper;

import com.pbcompass.cursos.dto.MatriculadosDto;
import com.pbcompass.cursos.dto.BuscarTodosCursosRespostaDto;
import com.pbcompass.cursos.dto.CursoCadastrarDto;
import com.pbcompass.cursos.dto.CursoRespostaDto;
import com.pbcompass.cursos.entities.Curso;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CursoMapper {

    public static Curso toCurso(CursoCadastrarDto dto) {
        return new ModelMapper().map(dto, Curso.class);
    }

    public static Curso toCurso(CursoRespostaDto dto) {
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

    public static BuscarTodosCursosRespostaDto toBuscarTodosCursosRespostaDto(Curso curso) {
        return new ModelMapper().map(curso, BuscarTodosCursosRespostaDto.class);
    }

    public static List<BuscarTodosCursosRespostaDto> toListBuscarTodosCursosRespostaDto(List<Curso> cursoList) {
        return cursoList.stream().map(CursoMapper::toBuscarTodosCursosRespostaDto).toList();
    }

    public static MatriculadosDto toMatriculadosDto(Curso curso){
        String professor = curso.getProfessor().getNome();
        PropertyMap<Curso, MatriculadosDto> props = new PropertyMap<Curso, MatriculadosDto>() {
            protected void configure() {
                map().setProfessor(professor);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(curso, MatriculadosDto.class);
    }
}
