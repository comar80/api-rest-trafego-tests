# language: pt
@regressivo
Funcionalidade: Deletar um motorista
  Como usuário da API
  Quero conseguir deletar um motorista
  Para que o registro seja apagado corretamente no sistema

  Contexto: Cadastro bem-sucedido de motorista
    Dado que eu tenha os seguintes dados do motorista:
      | campo         | valor        |
      | idMotorista   | 1            |
      | nome          | João Silva   |
      | cpf           | 12345678900  |
      | endereco      | Rua A, 123   |
      | telefone      | 11987654321  |
      | cnhNumero     | 123456789    |
      | cnhCategoria  | B            |
      | cnhValidade   | 2025-12-31   |
    Quando eu enviar a requisição para o endpoint "/api/motoristas" de cadastro de motoristas
    Então o status code da resposta do motorista deve ser 201

  Cenário: Deve ser possível deletar um motorista
    Dado que eu recupere o ID do motorista criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/motoristas" de deleção de motorista
    Então o status code da resposta do motorista deve ser 204

  Cenário: Deve ser possível buscar um motorista pelo ID
    Dado que eu recupere o ID do motorista criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/motoristas" de busca de motorista
    Então o status code da resposta do motorista deve ser 200
