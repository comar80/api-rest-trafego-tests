# language: pt
@regressivo
Funcionalidade: Cadastro de novo motorista
  Como usuário da API
  Quero cadastrar um novo motorista
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de motorista
    Dado que eu tenha os seguintes dados do motorista:
      | campo         | valor             |
      | idMotorista   | 1                 |
      | nome          | João Silva        |
      | cpf           | 12345678900       |
      | endereco      | Rua A, 123        |
      | telefone      | (11) 91234-5678   |
      | cnhNumero     | 987654321         |
      | cnhCategoria  | B                 |
      | cnhValidade   | 2025-12-31        |
    Quando eu enviar a requisição para o endpoint "/api/motoristas" de cadastro de motoristas
    Então o status code da resposta do motorista deve ser 201

  Cenário: Cadastro de motorista sem sucesso ao não passar o campo CPF
    Dado que eu tenha os seguintes dados do motorista:
      | campo         | valor             |
      | idMotorista   | 1                 |
      | nome          | João Silva        |
      | endereco      | Rua A, 123        |
      | telefone      | (11) 91234-5678   |
      | cnhNumero     | 987654321         |
      | cnhCategoria  | B                 |
      | cnhValidade   | 2025-12-31        |
    Quando eu enviar a requisição para o endpoint "/api/motoristas" de cadastro de motoristas
    Então o status code da resposta do motorista deve ser 400
    E a resposta de erro da api do motorista deve retornar o json "{\"cpf\":\"O cpf é obrigatório!\"}"
