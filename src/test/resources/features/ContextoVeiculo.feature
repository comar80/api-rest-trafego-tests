# language: pt
@regressivo
Funcionalidade: Deletar um veículo
  Como usuário da API
  Quero conseguir deletar um veículo
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de veículo
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

  Cenário: Deve ser possível deletar um veículo
    Dado que eu recupere o ID do veículo criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/veiculos" de deleção de veículo
    Então o status code da resposta do veículo deve ser 204

  Cenário: Deve ser possível buscar um veículo pelo ID
    Dado que eu recupere o ID do veículo criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/veiculos" de busca de veículo
    Então o status code da resposta do veículo deve ser 200