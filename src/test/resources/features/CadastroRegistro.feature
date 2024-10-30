# language: pt
@regressivo
Funcionalidade: Cadastro de novo registro
  Como usuário da API
  Quero cadastrar um novo registro
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de registro
    Dado que eu tenha os seguintes dados do registro:
      | campo                     | valor          |
      | idRegistroTrafego         | 1              |
      | idVeiculo                 | 1              |
      | idMotorista               | 1              |
      | idRota                    | 1              |
      | dataHora                  | 2024-10-30T10:00:00 |
      | velocidadeVeiculo         | 80             |
      | condicoesMetereologicas   | Céu limpo    |
      | outrosDados               | Dados adicionais |
    Quando eu enviar a requisição para o endpoint "/api/registros" de cadastro de registro
    Então o status code da resposta do registro deve ser 201

  Cenário: Cadastro de registro sem sucesso ao não passar o campo dataHora
    Dado que eu tenha os seguintes dados do registro:
      | campo                     | valor          |
      | idRegistroTrafego         | 1              |
      | idVeiculo                 | 1              |
      | idMotorista               | 1              |
      | idRota                    | 1              |
      | velocidadeVeiculo         | 80             |
      | condicoesMetereologicas   | Céu limpo    |
      | outrosDados               | Dados adicionais |
    Quando eu enviar a requisição para o endpoint "/api/registros" de cadastro de registro
    Então o status code da resposta do registro deve ser 400
    E a resposta de erro da api do registro deve retornar o json "{\"dataHora\":\"Data e Hora é obrigatório\"}"
