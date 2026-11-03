🌱 API ESG - Telemetria Energética & Testes AutomatizadosProjeto focado em qualidade de software e sustentabilidade (ESG), desenvolvido para simular a telemetria de consumo elétrico e validar regras de negócio através de testes automatizados de API e BDD (Behavior Driven Development).💡 O que o projeto faz?A aplicação recebe dados de consumo energético de dispositivos e calcula automaticamente a emissão estimada de carbono:$$\text{Emissão de CO}_2\text{ (kg)} = \text{Consumo em kWh} \times 0.08$$Se o consumo for menor ou igual a zero, o sistema bloqueia o registro e retorna uma mensagem de erro padronizada.🛠️ Tecnologias UsadasLinguagem: Java 17+Framework: Spring Boot 3Testes de API: RestAssuredBDD (Cenários em Português): Cucumber (Gherkin)Validação de Estrutura: JSON Schema🧪 Cenários de Teste (BDD)Os testes foram escritos em português claro no arquivo src/test/resources/features/telemetria_esg.feature:Cadastro com Sucesso (Caminho Feliz):Envia uma medição de 150 kWh.Valida resposta 201 Created, status REGISTRADO, emissão calculada de 12.0 kg de $\text{CO}_2$ e obediência ao contrato JSON Schema.Rejeição de Valor Inválido (Caminho Negativo):Tenta registrar consumo igual a 0.0 kWh.Valida o retorno do status 400 Bad Request com a mensagem: "O consumo em kWh deve ser maior que zero".Listagem Geral (GET):Garante a existência de registros e consulta a base.Valida o status 200 OK e o contrato em formato de lista (Array) via JSON Schema.▶️ Como Rodar os Testes1. Pelo TerminalNa pasta raiz do projeto, execute:Bash./mvnw clean test
(ou mvn clean test caso tenha o Maven instalado)2. Pelo IntelliJ IDEAAbra a pasta src/test/java/com/java/projetofiap/dta/runner/Clique com o botão direito no arquivo CucumberRunnerTest.javaSelecione Run 'CucumberRunnerTest'📊 Relatório Visual de TestesApós rodar a suíte, o Cucumber gera automaticamente um relatório em HTML com todos os passos detalhados em verde.Onde encontrar:Plaintexttarget/cucumber-reports/report.html
Dica: Clique com o botão direito no arquivo report.html no IntelliJ e escolha Open In -> Browser para ver o relatório completo no navegador.📂 Organização das PastasPlaintextsrc/
├── main/java/.../dta/
│   ├── controller/      # Endpoints da API REST e tratamento de erros
│   ├── dto/             # Objetos de envio e resposta com Bean Validation
│   └── service/         # Regras de negócio e cálculo de emissões ESG
└── test/
    ├── java/.../dta/
    │   ├── runner/      # Classe que dispara a suíte de testes
    │   └── steps/       # Código Java (RestAssured) que executa os passos BDD
    └── resources/
        ├── features/    # Cenários em Gherkin (.feature)
        └── schemas/     # Contratos estruturais (.json schema)
