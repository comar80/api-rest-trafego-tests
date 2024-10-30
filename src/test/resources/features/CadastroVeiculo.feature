# language: pt
@regressivo
Funcionalidade: Cadastro de novo veículo
  Como usuário da API
  Quero cadastrar um novo veículo
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de veículo
    Dado que eu tenha os seguintes dados do veículo:
      | campo          | valor          |
      | idVeiculo      | 1              |
      | tipoVeiculo    | SUV            |
      | modelo         | HONDA HRV      |
      | anoFabricacao  | 2024           |
      | placa          | ABC1B34        |
      | outrosDetalhes | PCD            |
    Quando eu enviar a requisição para o endpoint "/api/veiculos" de cadastro de veículos
    Então o status code da resposta do veículo deve ser 201

  Cenário: Cadastro de veículo sem sucesso ao passar o campo ano inválido
    Dado que eu tenha os seguintes dados do veículo:
      | campo          | valor          |
      | idVeiculo      | 1              |
      | tipoVeiculo    | SUV            |
      | modelo         | HONDA HRV      |
      | anoFabricacao  | 0              |
      | placa          | ABC1B34        |
      | outrosDetalhes | PCD            |
    Quando eu enviar a requisição para o endpoint "/api/veiculos" de cadastro de veículos
    Então o status code da resposta do veículo deve ser 400
    E a resposta de erro da api do veículo deve retornar o json "{\"anoFabricacao\":\"O ano deve ser maior que 0\"}"