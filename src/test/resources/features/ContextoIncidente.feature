# language: pt
@regressivo
Funcionalidade: Deletar um incidente
  Como usuário da API
  Quero conseguir deletar um incidente
  Para que o registro seja apagado corretamente no sistema

  Contexto: Cadastro bem-sucedido de incidente
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

  Cenário: Deve ser possível deletar um incidente
    Dado que eu recupere o ID do incidente criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/incidentes" de deleção de incidente
    Então o status code da resposta do incidente deve ser 204

  Cenário: Deve ser possível buscar um incidente pelo ID
    Dado que eu recupere o ID do incidente criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/incidentes" de busca de incidente
    Então o status code da resposta do incidente deve ser 200
