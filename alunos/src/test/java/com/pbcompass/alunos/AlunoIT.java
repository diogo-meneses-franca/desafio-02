package com.pbcompass.alunos;

import com.pbcompass.alunos.dto.AlunoCadastrarDto;
import com.pbcompass.alunos.dto.AlunoRespostaDto;
import com.pbcompass.alunos.exception.MensagemErroPadrao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql//alunos/cadastrar.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/alunos/deletar.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AlunoIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void cadastrarAluno_ComDadosValidos_RetornaAlunoRespostaDtoComStatus201(){
        AlunoRespostaDto resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("Carlos", "98815553029", LocalDate.of(1986, 5, 26), "M"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AlunoRespostaDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getId()).isNotNull();
        Assertions.assertThat(resposta.getNome()).isEqualTo("Carlos");
        Assertions.assertThat(resposta.getSexo()).isEqualTo("M");
        Assertions.assertThat(resposta.getAtivo()).isTrue();
    }

    @Test
    public void cadastrarAluno_ComCpfJaCadastrado_RetornaMensagemDeErroPadraoComStatus409(){
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("Carlos", "35211170229", LocalDate.of(1986, 5, 26), "M"))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(409);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("CPF '35211170229' já cadastrado");
    }

    @Test
    public void cadastrarAluno_ComDadosInvalidos_RetornaMensagemDeErroPadraoComStatus422(){
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("", "", LocalDate.of(1986, 5, 26), ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Dados de entrada inválidos");
    }

    @Test
    public void cadastrarAluno_ComCpfSomenteZeros_RetornaMensagemDeErroPadraoComStatus422() {
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("Carlos", "00000000000", LocalDate.of(1986, 5, 26), "M"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Dados de entrada inválidos");
    }

    @Test
    public void cadastrarAluno_ComCpfEmBranco_RetornaMensagemDeErroPadraoComStatus422() {
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("Carlos", "", LocalDate.of(1986, 5, 26), "M"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Dados de entrada inválidos");
    }

    @Test
    public void cadastrarAluno_ComCpfDeTamanhoInvalido_RetornaMensagemDeErroPadraoComStatus422() {
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("Carlos", "988155530292", LocalDate.of(1986, 5, 26), "M"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Dados de entrada inválidos");
    }

    @Test
    public void cadastrarAluno_ComSexoEmBranco_RetornaMensagemDeErroPadraoComStatus422() {
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("Carlos", "98815553029", LocalDate.of(1986, 5, 26), ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Dados de entrada inválidos");
    }

    @Test
    public void cadastrarAluno_ComNomeEmBranco_RetornaMensagemDeErroPadraoComStatus422() {
        MensagemErroPadrao resposta = testClient.post()
                .uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AlunoCadastrarDto("", "98815553029", LocalDate.of(1986, 5, 26), "M"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Dados de entrada inválidos");
    }

    @Test
    public void buscarAluno_ComIdExistente_RetornaAlunoRespostaDtoComStatus200(){
        AlunoRespostaDto resposta = testClient.get()
                .uri("/api/alunos/10", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AlunoRespostaDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getId()).isEqualTo(10);
        Assertions.assertThat(resposta.getNome()).isEqualTo("Joao");
    }

    @Test
    public void buscarAluno_ComIdInexistente_RetornaMensagemDeErroPadraoComStatus404(){
        MensagemErroPadrao resposta = testClient.get()
                .uri("/api/alunos/0", 1)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(404);
        Assertions.assertThat(resposta.getMensagem()).isEqualTo("Aluno com id 0 não encontrado");
    }

    @Test
    public void inativarAluno_ComIdExistente_RetornaAlunoRespostaDtoComStatus200(){
        AlunoRespostaDto resposta = testClient.put()
                .uri("/api/alunos/inativar/10", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AlunoRespostaDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getAtivo()).isEqualTo(false);
    }

    @Test
    public void inativarAluno_ComIdInexistente_RetornaMensagemDeErroPadraoComStatus404(){
        MensagemErroPadrao resposta = testClient.put()
                .uri("/api/alunos/inativar/0", 1)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(MensagemErroPadrao.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(resposta).isNotNull();
        Assertions.assertThat(resposta.getStatus()).isEqualTo(404);
    }
}
