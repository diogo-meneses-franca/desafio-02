package com.pbcompass.cursos.dto.mapper;

import com.pbcompass.cursos.dto.ProfessorCriarDto;
import com.pbcompass.cursos.dto.ProfessorRespostaDto;
import com.pbcompass.cursos.entities.Professor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfessorMapper {

    public static Professor toProfessor(ProfessorCriarDto dto){
        return new ModelMapper().map(dto, Professor.class);
    }

    public static ProfessorRespostaDto toRespostaDto(Professor professor){
        return new ModelMapper().map(professor, ProfessorRespostaDto.class);
    }

    public static List<ProfessorRespostaDto> toListaDto(List<Professor> profList) {
        return profList.stream()
                .map(ProfessorMapper::toRespostaDto)
                .collect(Collectors.toList());
    }
}
