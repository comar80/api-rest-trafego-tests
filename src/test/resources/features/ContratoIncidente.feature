# language: pt
@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de incidente
  Como usuário da API
  Quero cadastrar um novo incidente bem-sucedido
  Para que eu consiga validar se o contrato está conforme o esperado

  Cenario: Validar contrato do cadastro bem-sucedido de incidente
    Dado que eu tenha os seguintes dados do incidente:
      | campo          | valor                 |
      | idIncidente    | 1                     |
      | tipoIncidente  | Acidente de trânsito  |
      | localizacao    | Avenida Central, 123  |
      | descricao      | Colisão entre veículos|
      | dataHora       | 2024-10-30T14:00:00   |
      | status         | Pendente              |
    Quando eu enviar a requisição para o endpoint "/api/incidentes" de cadastro de incidentes
    Então o status code da resposta do incidente deve ser 201
    E que o arquivo de contrato do incidente esperado é o "Cadastro bem-sucedido de incidente"
    Então a resposta da requisição do incidente deve estar em conformidade com o contrato selecionado
