# language: pt
@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de motorista
  Como usuário da API
  Quero cadastrar um novo motorista bem-sucedido
  Para que eu consiga validar se o contrato está conforme o esperado

  Cenario: Validar contrato do cadastro bem-sucedido de motorista
    Dado que eu tenha os seguintes dados do motorista:
      | campo         | valor        |
      | idMotorista   | 1            |
      | nome          | João Silva   |
      | cpf           | 12345678900  |
      | endereco      | Rua A, 123   |
      | telefone      | 11987654321  |
      | cnhNumero     | 123456789    |
      | cnhCategoria  | B            |
      | cnhValidade   | 2025-12-31   |
    Quando eu enviar a requisição para o endpoint "/api/motoristas" de cadastro de motoristas
    Então o status code da resposta do motorista deve ser 201
    E que o arquivo de contrato do motorista esperado é o "Cadastro bem-sucedido de motorista"
    Então a resposta da requisição do motorista deve estar em conformidade com o contrato selecionado
