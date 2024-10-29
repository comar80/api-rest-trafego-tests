# language: pt
@regressivo
Funcionalidade: Deletar um usuário
  Como usuário da API
  Quero conseguir deletar um usuário
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo          | valor          |
      | usuarioId      | 1              |
      | nome           | User 1         |
      | email          | user1@mail.com |
      | senha          | Senha@123      |
      | role           | ADMIN          |
    Quando eu enviar a requisição para o endpoint "/api/usuarios" de cadastro de usuários
    Então o status code da resposta do usuário deve ser 201

  Cenário: Deve ser possível deletar um usuário
    Dado que eu recupere o ID do usuário criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/usuarios" de deleção de usuário
    Então o status code da resposta do usuário deve ser 204

  Cenário: Deve ser possível buscar um usuário pelo ID
    Dado que eu recupere o ID do usuário criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/usuarios" de busca de usuário
    Então o status code da resposta do usuário deve ser 200