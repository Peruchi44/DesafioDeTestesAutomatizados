# language: pt
Funcionalidade: Monitoramento de Eficiencia Energetica ESG
      Como auditor ambiental da organizacao
      Quero registrar e monitorar os dados de consumo de energia
      Para certificar as metas de sustentabilidade e controle de emissoes de carbono

      Contexto:
            Dado que o servico de telemetria ESG esta disponivel

  # Cenario 1: Caminho Feliz (POST com validacao de status, corpo e contrato)
      Cenario: Registrar com sucesso uma nova medicao de consumo energetico
            Quando eu enviar uma requisicao POST para "/api/v1/telemetria/consumo" com o seguinte corpo:
      """
      {
        "dispositivoId": "IOT-PREDIO-A",
        "setor": "DataCenter",
        "consumoKwh": 150.0
      }
      """
            Entao o codigo de status da resposta deve ser 201
            E o campo "status" da resposta deve ser "REGISTRADO"
            E o campo "setor" da resposta deve ser "DataCenter"
            E o campo "emissaoEstimadaKgCo2" da resposta deve ser "12.0"
            E o corpo da resposta deve validar com o contrato "schemas/telemetria-schema.json"

  # Cenario 2: Caminho Negativo (Consumo invalido com validacao de erro)
      Cenario: Rejeitar registro com consumo de energia menor ou igual a zero
            Quando eu enviar uma requisicao POST para "/api/v1/telemetria/consumo" com o seguinte corpo:
      """
      {
        "dispositivoId": "IOT-PREDIO-A",
        "setor": "DataCenter",
        "consumoKwh": 0.0
      }
      """
            Entao o codigo de status da resposta deve ser 400
            E o campo "erro" da resposta deve ser "O consumo em kWh deve ser maior que zero"

  # Cenario 3: Consulta e Validacao de Lista (GET com contrato de array)
      Cenario: Consultar a lista de todas as medicoes registradas
            Dado que existe pelo menos uma medicao registrada no sistema
            Quando eu enviar uma requisicao GET para "/api/v1/telemetria/consumo"
            Entao o codigo de status da resposta deve ser 200
            E o corpo da resposta deve validar com o contrato de lista "schemas/telemetria-lista-schema.json"