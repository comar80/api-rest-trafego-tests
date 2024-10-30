# language: pt
@regressivo
Funcionalidade: Cadastro de novo incidente
  Como usuário da API
  Quero cadastrar um novo incidente
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de incidente
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

  Cenário: Cadastro de incidente sem sucesso ao não passar o campo status
    Dado que eu tenha os seguintes dados do incidente:
      | campo          | valor                 |
      | idIncidente    | 1                     |
      | tipoIncidente  | Acidente de trânsito  |
      | localizacao    | Avenida Central, 123  |
      | descricao      | Colisão entre veículos|
      | dataHora       | 2024-10-30T14:00:00   |
    Quando eu enviar a requisição para o endpoint "/api/incidentes" de cadastro de incidentes
    Então o status code da resposta do incidente deve ser 400
    E a resposta de erro da api do incidente deve retornar o json "{\"status\":\"Status é obrigatório!\"}"
