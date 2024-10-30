# language: pt
@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de veículo
  Como usuário da API
  Quero cadastrar um novo veículo bem-sucedido
  Para que eu consiga validar se o contrato está conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido de veículo
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
    E que o arquivo de contrato do veículo esperado é o "Cadastro bem-sucedido de veículo"
    Então a resposta da requisição do veículo deve estar em conformidade com o contrato selecionado