# language: pt
@regressivo
Funcionalidade: Cadastro de novo usuário
  Como usuário da API
  Quero cadastrar um novo usuário
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo          | valor          |
      | usuarioId      | 1              |
      | nome           | User 1         |
      | email          | user1@mail.com |
      | senha          | Senha@123      |
      | role           | ADMIN          |
    Quando eu enviar a requisição para o endpoint "/api/usuarios" de cadastro de usuários
    Então o status code da resposta do usuário deve ser 201

  Cenário: Cadastro de usuário sem sucesso ao passar o campo email inválido
    Dado que eu tenha os seguintes dados do usuário:
      | campo          | valor          |
      | usuarioId      | 1              |
      | nome           | User 1         |
      | email          | user1          |
      | senha          | Senha@123      |
      | role           | ADMIN          |
    Quando eu enviar a requisição para o endpoint "/api/usuarios" de cadastro de usuários
    Então o status code da resposta do usuário deve ser 400
    E a resposta de erro da api do usuário deve retornar o json "{\"email\":\"O e-mail do usuário não é válido!\"}"