# language: pt
@regressivo
Funcionalidade: Deletar uma rota
  Como usuário da API
  Quero conseguir deletar uma rota
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de rota
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

  Cenário: Deve ser possível deletar uma rota
    Dado que eu recupere o ID da rota criada no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/rotas" de deleção de rota
    Então o status code da resposta da rota deve ser 204

  Cenário: Deve ser possível buscar uma rota pelo ID
    Dado que eu recupere o ID da rota criada no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/rotas" de busca de rota
    Então o status code da resposta da rota deve ser 200