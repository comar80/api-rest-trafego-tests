# language: pt
@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de rota
  Como usuário da API
  Quero cadastrar uma nova rota bem-sucedida
  Para que eu consiga validar se o contrato está conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido de rota
    Dado que eu tenha os seguintes dados da rota:
      | campo                     | valor                             |
      | idRota                    | 1                                 |
      | origem                    | Avenida Paulista, 1, São Paulo    |
      | destino                   | Avenida Paulista, 3000, São Paulo |
      | distancia                 | 3000                              |
      | tempoMedio                | 10                                |
      | condicoesEspeciais        | tráfego lento                     |
      | velocidadeMaximaPermitida | 50                                |
    Quando eu enviar a requisição para o endpoint "/api/rotas" de cadastro de rotas
    Então o status code da resposta da rota deve ser 201
    E que o arquivo de contrato da rota esperado é o "Cadastro bem-sucedido de rota"
    Então a resposta da requisição da rota deve estar em conformidade com o contrato selecionado