# Micro serviço de Gestão de Cursos

A API de Gestão de Cursos é uma aplicação que permite o gerenciamento de cursos, incluindo cadastro, consulta e
manipulação de dados relacionados aos cursos oferecidos. Ela integra-se com outra API de Alunos e Matrículas, utilizando
o OpenFeign para comunicação entre as APIs e associação entre os dados.

## Funcionalidades Principais

- Cadastro, consulta e manipulação de cursos.
- Integração com outra API de Alunos e Matrículas.
- Associação de dados entre as APIs para matrículas em cursos.

## Estrutura do Projeto

* Arquitetura REST: A API é baseada em uma arquitetura REST, onde os recursos são representados por entidades sendo
  acessados por endpoints RESTful.
* Entidades: As entidades do sistema possuem mapeamento JPA (Java Persistence API), garantindo a persistência dos dados
  no banco de dados. O curso, por exemplo, possui um enum com constantes para as áreas de conhecimento.
* DTOS: utilizado para modelar o que aparece ou não na requisição
* Repositórios: Utiliza o JpaRepository, que fornece métodos padrão para operações de banco de dados, simplificando o
  acesso aos dados.
* Serviços: Os serviços realizam o tratamento de exceções e a lógica de negócios, utilizando métodos dos repositórios
  para manipular os dados de forma eficiente.
* Controladores: Responsáveis por definir os endpoints da API e processar as requisições HTTP, utilizando os serviços
  para executar as operações necessárias.

# Endpoints

| Método | URL                                      | Descrição                                               |
|--------|------------------------------------------|---------------------------------------------------------|
| `POST` | /api/cursos                              | Cadastra um novo curso no sistema.                      |
| `GET`  | /api/cursos                              | Recupera todos os cursos cadastrados no sistema.        |
| `GET`  | /api/cursos/{id}                         | Recupera um curso específico pelo seu ID.               |
| `GET`  | /api/cursos/nome/{nome}                  | Recupera um curso específico pelo seu nome.             |
| `PUT`  | /api/cursos                              | Altera qualquer item em um curso.                       |
| `PUT`  | /api/cursos/matricular/{cursoId}         | Matricula um aluno em um curso específico.              |
| `PUT`  | /api/cursos/inativar-matricula/{cursoId} | Inativa a matrícula de um aluno em um curso específico. |
| `POST` | /api/professores                         | Cadastrar um novo professor.                            |
| `GET`  | /api/professores/{id}                    | Buscar um professor por ID.                             |
| `GET`  | /api/professores/nome/{nome}             | Buscar um professor por nome.                           |
| `PUT`  | /api/professores                         | Alterar os detalhes de um professor existente.          |
| `GET`  | /api/professores                         | Buscar todos os professores cadastrados.                |

## Documentação dos endpoints

### Cadastrar um novo curso

- **Método**: `cadastrar`
- **Descrição**: Este método permite cadastrar um novo curso.
- **Funcionamento**: Recebe um objeto `CursoCadastrarDto` contendo os dados do curso a ser cadastrado. Em seguida,
  converte esse objeto para a entidade `Curso`, realiza o cadastro chamando o serviço `cadastrar`, e retorna um
  objeto `CursoRespostaDto` com os dados do curso cadastrado.
- **Códigos de Resposta**:
    - `201`: Curso cadastrado com sucesso. Retorna o objeto `CursoRespostaDto` correspondente ao curso cadastrado.
    - `422`: Se os dados de entrada forem inválidos, retorna uma mensagem de erro padrão.

### Buscar todos os cursos

- **Método**: `buscarTodos`
- **Descrição**: Este método retorna todos os cursos cadastrados.
- **Funcionamento**: Chama o serviço `buscarTodos`, que retorna uma lista de cursos. Em seguida, converte essa lista
  para uma lista de objetos `CursoRespostaDto` e retorna essa lista.
- **Códigos de Resposta**:
    - `200`: Recursos recuperados com sucesso. Retorna uma lista de objetos `CursoRespostaDto`.
    - `404`: Se não houver nenhum registro encontrado, retorna uma mensagem de erro padrão.

## Buscar curso por ID

- **Método**: `buscarPorId`
- **Descrição**: Este método busca um curso pelo ID especificado.
- **Funcionamento**: Recebe o ID do curso como parâmetro, chama o serviço `buscarPorId`, que retorna o curso
  correspondente. Retorna então o objeto `CursoRespostaDto` com os dados do curso encontrado.
- **Códigos de Resposta**:
    - `200`: Recurso recuperado com sucesso. Retorna o objeto `CursoRespostaDto`.
    - `404`: Se o recurso não for encontrado, retorna uma mensagem de erro padrão.

### Buscar curso por nome

- **Método**: `buscarPorNome`
- **Descrição**: Este método busca um curso pelo nome especificado.
- **Funcionamento**: Recebe o nome do curso como parâmetro, chama o serviço `buscarPorNome`, que retorna o curso
  correspondente. Retorna então o objeto `CursoRespostaDto` com os dados do curso encontrado.
- **Códigos de Resposta**:
    - `200`: Recurso recuperado com sucesso. Retorna o objeto `CursoRespostaDto`.
    - `404`: Se o recurso não for encontrado, retorna uma mensagem de erro padrão.

### Alterar curso

- **Método**: `alterar`
- **Descrição**: Este método permite alterar qualquer item no curso.
- **Funcionamento**: Recebe um objeto `CursoRespostaDto` contendo os dados atualizados do curso. Converte esse objeto
  para a entidade `Curso` e chama o serviço `alterar` para realizar a alteração. Retorna então o
  objeto `CursoRespostaDto` com os dados do curso alterado.
- **Códigos de Resposta**:
    - `200`: Recurso alterado com sucesso. Retorna o objeto `CursoRespostaDto` correspondente ao curso alterado.
    - `400`: Se o corpo da requisição for inválido, retorna uma mensagem de erro padrão.
    - `404`: Se o item a ser atualizado não for encontrado, retorna uma mensagem de erro padrão.

### Matricular aluno em um curso

- **Método**: `matricular`
- **Descrição**: Este método permite matricular um aluno em um curso.
- **Funcionamento**: Recebe o ID do curso e um objeto `AlunoDto` contendo os dados do aluno a ser matriculado. Chama o
  serviço `matricular` para realizar a matrícula. Retorna então o objeto `CursoRespostaDto` com os dados do curso após a
  matrícula.
- **Códigos de Resposta**:
    - `200`: Aluno matriculado com sucesso. Retorna o objeto `CursoRespostaDto` correspondente ao curso após a
      matrícula.
  ``` 
    {
        "id": "1"
    }
  ```

### Inativar matrícula de um aluno em um curso

- **Método**: `inativarMatricula`
- **Descrição**: Este método permite inativar a matrícula de um aluno em um curso.
- **Funcionamento**: Recebe o ID do curso e um objeto `AlunoDto` contendo os dados do aluno cuja matrícula será
  inativada. Chama o serviço `inativarMatricula` para realizar a operação. Retorna então o objeto `CursoRespostaDto` com
  os dados do curso após a inativação da matrícula.
- **Códigos de Resposta**:
    - `200`: Matrícula inativada com sucesso. Retorna o objeto `CursoRespostaDto` correspondente ao curso após a
      inativação da matrícula.
  ``` 
    {
        "id": "1"
    }
  ```

### Cadastrar um novo professor

- **Método**: `cadastrar`
- **Descrição**: Este método permite cadastrar um novo professor.
- **Funcionamento**: Recebe um objeto `ProfessorCadastrarDto` contendo os dados do professor a ser cadastrado. Em
  seguida, converte esse objeto para a entidade `Professor`, realiza o cadastro chamando o serviço `cadastrar`, e
  retorna um objeto `ProfessorRespostaDto` com os dados do professor cadastrado.
  - **Códigos de Resposta**:
      - `201`: Professor cadastrado com sucesso. Retorna o objeto `ProfessorRespostaDto` correspondente ao professor
        cadastrado.
      - `422`: Se os dados de entrada forem inválidos, retorna uma mensagem de erro padrão.
  ``` 
    {
        "nome": "professor"
    }
  ```


### Buscar professor por ID

- **Método**: `buscarPorId`
- **Descrição**: Este método busca um professor pelo ID especificado.
- **Funcionamento**: Recebe o ID do professor como parâmetro, chama o serviço `buscarPorId`, que retorna o professor
  correspondente. Retorna então o objeto `ProfessorRespostaDto` com os dados do professor encontrado.
- **Códigos de Resposta**:
    - `200`: Recurso recuperado com sucesso. Retorna o objeto `ProfessorRespostaDto`.
    - `404`: Se o recurso não for encontrado, retorna uma mensagem de erro padrão.

### Buscar professor por nome

- **Método**: `buscarPorNome`
- **Descrição**: Este método busca um professor pelo nome especificado.
- **Funcionamento**: Recebe o nome do professor como parâmetro, chama o serviço `buscarPorNome`, que retorna o professor
  correspondente. Retorna então o objeto `ProfessorRespostaDto` com os dados do professor encontrado.
- **Códigos de Resposta**:
    - `200`: Recurso recuperado com sucesso. Retorna o objeto `ProfessorRespostaDto`.
    - `404`: Se o recurso não for encontrado, retorna uma mensagem de erro padrão.

### Alterar professor

- **Método**: `alterar`
- **Descrição**: Este método permite alterar qualquer item no professor.
- **Funcionamento**: Recebe um objeto `ProfessorCadastrarDto` contendo os dados atualizados do professor. Converte esse
  objeto para a entidade `Professor` e chama o serviço `alterar` para realizar a alteração. Retorna então o
  objeto `ProfessorRespostaDto` com os dados do professor alterado.
- **Códigos de Resposta**:
    - `200`: Recurso alterado com sucesso. Retorna o objeto `ProfessorRespostaDto` correspondente ao professor alterado.
    - `400`: Se o corpo da requisição for inválido, retorna uma mensagem de erro padrão.
    - `404`: Se o item a ser atualizado não for encontrado, retorna uma mensagem de erro padrão.
  ``` 
    {
        "nome": "novoProfessor"
    }
  ```


### Buscar todos os professores

- **Método**: `buscarTodos`
- **Descrição**: Este método retorna todos os professores cadastrados.
- **Funcionamento**: Chama o serviço `buscarTodos`, que retorna uma lista de professores. Em seguida, converte essa
  lista para uma lista de objetos `ProfessorRespostaDto` e retorna essa lista.
- **Códigos de Resposta**:
    - `200`: Recursos recuperados com sucesso. Retorna uma lista de objetos `ProfessorRespostaDto`.
    - `404`: Se não houver nenhum registro encontrado, retorna uma mensagem de erro padrão.

## Instruções de Uso

1. Execute cada micro serviço separadamente
2. Cadastre o professor primeiro
3. cadastre o curso  
