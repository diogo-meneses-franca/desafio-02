package com.pbcompass.alunos.domain;

import com.pbcompass.alunos.repository.AlunoRepository;
import com.pbcompass.alunos.service.AlunoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

}
