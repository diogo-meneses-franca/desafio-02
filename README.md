# desafio-02

## Gestão de Cursos e Alunos

Este projeto consiste em dois micros serviços para a gestão de cursos e alunos/matriculas, seguindo as melhores práticas e utilizando as tecnologias especificadas.

## Requisitos 
    
* Utilização de boas práticas;
* Utilização banco de dados H2 em arquivos; independentes para cada aplicação;
* Criação de uma collection no postman com todos os endponts;
* Todos os endponts devem obedecer a suas finalidades dos métodos HTTP, além de status code coerentes.

# Micro serviço de Gestão de Cursos

## Entidades

* Curso 
    * nome  
    * quantidade de horas 
    * professor 
    * área do conhecimento
    * ativo

* Professor
    * nome

## Funcionalidades 

- Cadastrar curso
- Inabilitar curso
- Alterar professor de um curso já cadastrado

# Micro serviço de Gestão de Alunos e Matrículas

## entidades 

* Aluno 
    * nome 
    * CPF
    * data de nascimento
    * sexo 
    * ativo 

## Funcionalidades

- Cadastrar alunos
- Matricular alunos a determinados cursos
- Inativar a matrícula de um aluno a curso
- Consultar alunos matriculados em um curso
- Inativar um aluno
    - Exemplo de JSON de retorno:

    ```
        "curso": "Economia familiar",
    	    "professor": "Maria Muller",
    	    "totalAlunos": 2,
    	    "alunos": [
    	        {"nome": "Adriano Alves", "sexo": "M", "ativo": true},
                {"nome": "Beatriz Bellon", "sexo": "F", "ativo": false}
    	    ]
    	}

    ```
## Regras 
* Apenas alunos ativos podem se matricular em cursos ativos
* Nomes de cursos não podem se repetir
* Não deve ser possível o cadastro de CPFs iguais
* Deve-se criar novas entidades e/ou campos caso necessário
* Deve-se criar todos os endpoints necessários
* Apenas 10 alunos ativos por curso são permitidos
* Se um aluno for inativado, todas as suas matrículas também deverão ser inativadas
* Todas as validações que o sistema possui devem retornar erros específicos com detalhamento sobre a validação
* O campo área do conhecimento deve ser um ENUM interno ao projeto com uma lista pré-definida, que deve constar na documentação

## Tecnologias Utilizadas 
*	Spring Boot para a criação dos micros serviços
*	H2 Database para o armazenamento dos dados
*	Swagger para documentação da API
*	OpenFeign para comunicação entre os micros serviços
*	JUnit e Mockito para testes unitários
*	Postman para teste e documentação dos endpoints

## Instruções de Uso
1.	Clone o repositório
2.	Execute cada micro serviço separadamente
3.	Acesse a documentação da API via Swagger para interagir com os endpoints
4.	Utilize a collection do Postman para testar os endpoints
