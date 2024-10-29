# language: pt
@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de usuário
  Como usuário da API
  Quero cadastrar um novo usuário bem-sucedido
  Para que eu consiga validar se o contrato está conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo          | valor          |
      | usuarioId      | 1              |
      | nome           | User 1         |
      | email          | user1@mail.com |
      | senha          | Senha@123      |
      | role           | ADMIN          |
    Quando eu enviar a requisição para o endpoint "/api/usuarios" de cadastro de usuários
    Então o status code da resposta do usuário deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de usuário"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado