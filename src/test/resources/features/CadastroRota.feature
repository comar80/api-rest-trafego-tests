# language: pt
@regressivo
Funcionalidade: Cadastro de nova rota
  Como usuário da API
  Quero cadastrar uma nova rota
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de rota
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

  Cenário: Cadastro de rota sem sucesso ao passar o campo distância inválido
    Dado que eu tenha os seguintes dados da rota:
      | campo                     | valor                             |
      | idRota                    | 1                                 |
      | origem                    | Avenida Paulista, 1, São Paulo    |
      | destino                   | Avenida Paulista, 3000, São Paulo |
      | distancia                 | 0                                 |
      | tempoMedio                | 10                                |
      | condicoesEspeciais        | tráfego lento                     |
      | velocidadeMaximaPermitida | 50                                |
    Quando eu enviar a requisição para o endpoint "/api/rotas" de cadastro de rotas
    Então o status code da resposta da rota deve ser 400
    E a resposta de erro da api da rota deve retornar o json "{\"distancia\":\"Deve ser maior que 0\"}"