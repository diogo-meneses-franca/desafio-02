# Micro serviço de Gestão de Alunos e Matrículas

A API de Gestão de Alunos e Matrículas é uma aplicação que permite o gerenciamento de alunos e suas matrículas, incluindo cadastro, consulta e manipulação de dados relacionados a eles. Ela integra-se com outra API de Cursos, utilizando o OpenFeign para comunicação entre as APIs e associação entre os dados.

## Funcionalidades Principais

- Cadastro, consulta e manipulação de alunos.
- Integração com outra API de Cursos.
- Associação de dados entre as APIs para        
matrículas em cursos.

## Estrutura do Projeto

* Arquitetura REST: A API é baseada em uma arquitetura REST, onde os recursos são representados por entidades e são acessados através de endpoints RESTful.
* Entidades: As entidades do sistema possuem mapeamento JPA (Java Persistence API), garantindo a persistência dos dados no banco de dados. O curso, por exemplo, possui um enum com constantes para as áreas de conhecimento.
* DTOS: utilizado para modelar o que aparece ou não na requisição 
* Repositórios: Utiliza o JpaRepository, que fornece métodos padrão para operações de banco de dados, simplificando o acesso aos dados.
* Serviços: Os serviços realizam o tratamento de exceções e a lógica de negócios, utilizando métodos dos repositórios para manipular os dados de forma eficiente.
* Controladores: Responsáveis por definir os endpoints da API e processar as requisições HTTP, utilizando os serviços para executar as operações necessárias.

# Endpoints 

| Método | URL | Descrição |
| ------------- |-------------| -------------|
| POST | /api/alunos | Cadastra um novo aluno no sistema. |
| GET | /api/alunos/{id} | Recupera um aluno específico pelo seu ID. |
| GET | /api/alunos/buscar-todos | Recupera todos os alunos cadastrados. |
| PUT | /api/alunos/inativar/{id} | Inativa a matrícula de um aluno. |
| PUT | /api/alunos/matricular/{alunoId} | Matricula um aluno em um curso. |


## Documentação dos endpoints 


### Cadastrar um novo aluno

- *Método*: cadastrar
- *Descrição*: Este método permite cadastrar um novo aluno.
- *Funcionamento*: Recebe um objeto AlunoCadastrarDto contendo os dados do aluno a ser cadastrado. Em seguida, converte esse objeto para a entidade Aluno, realiza o cadastro chamando o serviço cadastrar, e retorna um objeto AlunoRespostaDto com os dados do aluno cadastrado.
- *Códigos de Resposta*:
    - 201: Aluno cadastrado com sucesso. Retorna o objeto AlunoRespostaDto correspondente ao aluno cadastrado.
    - 409: Se os CPF já estiver cadastrado, retorna uma mensagem de erro padrão.
    - 422: Se os dados de entrada forem inválidos, retorna uma mensagem de erro padrão.
- *Corpo da requisição* : 

{ 
    "nome":"Aluno",
    "cpf": "92228338052",
    "dataNascimento": "1990-05-20",
    "sexo": "M"
}

### Buscar aluno por ID

- *Método*: buscarPorId
- *Descrição*: Este método busca um aluno pelo ID especificado.
- *Funcionamento*: Recebe o ID do aluno como parâmetro, chama o serviço buscarPorId, que retorna o aluno correspondente. Retorna então o objeto AlunoRespostaDto com os dados do aluno encontrado.
- *Códigos de Resposta*:
    - 200: Recurso recuperado com sucesso. Retorna o objeto AlunoRespostaDto.
    - 404: Se o recurso não for encontrado, retorna uma mensagem de erro padrão.


### Buscar todos os alunos

- *Método*: buscarTodos
- *Descrição*: Este método retorna todos os alunos cadastrados.
- *Funcionamento*: Chama o serviço buscarTodos, que retorna uma lista de alunos. Em seguida, converte essa lista para uma lista de objetos AlunoRespostaDto e retorna essa lista.
- *Códigos de Resposta*:
    - 200: Recursos recuperados com sucesso. Retorna uma lista de objetos ProfessorRespostaDto.
    - 404: Se não houver nenhum registro encontrado, retorna uma mensagem de erro padrão.


### Inativar um aluno

- *Método*: inativar
- *Descrição*: Este método permite inativar um aluno ativo.
- *Funcionamento*: Recebe o ID do aluno e chama o serviço inativar para realizar a operação. Retorna então o objeto AlunoRespostaDto com os dados do aluno após a inativação.
- *Códigos de Resposta*:
    - 200: Aluno inativado com sucesso. Retorna o objeto AlunoRespostaDto correspondente ao aluno após a inativação.
    - 404: Se o recurso não for encontrado, retorna uma mensagem de erro padrão.


### Matricular aluno em um curso

- *Método*: matricular
- *Descrição*: Este método permite matricular um aluno em um curso.
- *Funcionamento*: Recebe o ID do aluno e um objeto CursoMatricularDto contendo o ID do curso onde o aluno será matriculado. Chama o serviço matricular para realizar a matrícula. Retorna então o objeto CursoRespostaDto com os dados do curso após a matrícula.
- *Códigos de Resposta*:
    - 200: Aluno matriculado com sucesso. Retorna o objeto AlunoRespostaDto correspondente ao curso após a matrícula.
    - 422: Se o aluno já estiver cadastrado no curso, retorna mensagem de erro padrão.
    - 422: Caso haja erro de conexão entre as APIs durante processo, retorna mensagem de erro.




## Instruções de Uso
1.	Execute cada micro serviço separadamente
2.	Cadastre o professor primeiro 
3.	cadastre o curso
