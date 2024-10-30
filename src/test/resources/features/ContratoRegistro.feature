# language: pt
@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de registro
  Como usuário da API
  Quero cadastrar um novo registro bem-sucedido
  Para que eu consiga validar se o contrato está conforme o esperado
  Cenário: Validar contrato do cadastro bem-sucedido de registro
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
    E que o arquivo de contrato do registro esperado é o "Cadastro bem-sucedido de registro"
    Então a resposta da requisição do registro deve estar em conformidade com o contrato selecionado
