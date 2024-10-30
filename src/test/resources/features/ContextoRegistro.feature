# language: pt
@regressivo
Funcionalidade: Deletar um registro
  Como usuário da API
  Quero conseguir deletar um registro
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de registro
    Dado que eu tenha os seguintes dados do registro:
      | campo                      | valor          |
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

  Cenário: Deve ser possível deletar um registro
    Dado que eu recupere o ID do registro criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/registros" de deleção de registro
    Então o status code da resposta do registro deve ser 204

  Cenário: Deve ser possível buscar um registro pelo ID
    Dado que eu recupere o ID do registro criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/registros" de busca de registro
    Então o status code da resposta do registro deve ser 200
