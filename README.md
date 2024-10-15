# Projeto API para Diplomas

Este projeto é uma API desenvolvida para gerenciar diplomas e autenticar usuários utilizando JWT (JSON Web Token). A aplicação foi construída com **Spring Boot**, **Spring Security** e **JWT** para fornecer acesso seguro aos recursos da API, de acordo com o papel do usuário (`USER` ou `ADMIN`).

## Integrantes do Grupo

- Celeste Mayumi Pereira Tanaka RM552865
- Lívia Mariana Lopes RM552558
- Luana Vieira Santos da Silva RM552994


## Funcionalidades

- **Autenticação**: Usuários podem se registrar e realizar login.
- **Gerenciamento de Diplomas**: Usuários autenticados podem consultar seus diplomas cadastrados.
- **Autenticação JWT**: Todos os endpoints são protegidos por autenticação via token JWT.
- **Regras de Acesso**:
  - Usuários com o papel de **USER** podem acessar apenas o endpoint `GET /api/diplomas/{UUID}` para consultar seus diplomas.
  - Usuários com o papel de **ADMIN** têm acesso total a todos os endpoints.

## Regras de Negócio

- Ao realizar login, a API verifica se existem diplomas cadastrados no RG do usuário. Caso existam, os **UUIDs** dos diplomas são retornados no payload da resposta de login.
- O usuário com papel **USER** pode acessar o endpoint `GET /api/diplomas/{UUID}` para consultar um diploma específico utilizando um dos UUIDs retornados no login.
- O papel **ADMIN** tem permissão para criar, editar, e excluir diplomas, além de acessar todos os endpoints.

## Endpoints Principais

### Autenticação

- **POST /auth/register**: Registra um novo usuário.
- **POST /auth/login**: Realiza o login de um usuário e retorna um token JWT e, caso existam, os UUIDs dos diplomas vinculados ao RG do usuário.

### Diplomas

- **GET /api/diplomas/{UUID}**: Permite que um **USER** consulte um diploma específico por meio do UUID.
- **POST /api/diplomas**: Permite que um **ADMIN** adicione novos diplomas.
- **PUT /api/diplomas/{id}**: Permite que um **ADMIN** atualize um diploma existente.
- **DELETE /api/diplomas/{id}**: Permite que um **ADMIN** exclua um diploma.
  
## Observações
Para realizar requisições autenticadas, obtenha o token JWT no login e utilize-o no cabeçalho das requisições como Authorization: Bearer <seu_token>.
O projeto segue boas práticas de segurança com senhas encriptadas e tokens JWT para autenticação.
