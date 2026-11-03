package com.java.projetofiap.dta.steps;

import io.cucumber.java.pt.Entao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.java.projetofiap.dta.DtaApplication;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@CucumberContextConfiguration
@SpringBootTest(classes = DtaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TelemetriaSteps {

    @LocalServerPort
    private int port;

    private Response response;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Dado("que o servico de telemetria ESG esta disponivel")
    public void queOServicoDeTelemetriaEstaDisponivel() {
        // Contexto validado via inicialização do Spring Boot
    }

    @Quando("eu enviar uma requisicao POST para {string} com o seguinte corpo:")
    public void enviarRequisicaoPostComCorpo(String endpoint, String body) {
        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    @Entao("o codigo de status da resposta deve ser {int}")
    public void validarCodigoDeStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Entao("o campo {string} da resposta deve ser {string}")
    public void validarCampoTexto(String campo, String valorEsperado) {
        String valorAtual = response.jsonPath().getString(campo);
        assertEquals(valorEsperado, valorAtual,
                "Valor inesperado para o campo '" + campo + "'");
    }

    @E("o campo {string} deve ser igual a {string}")
    public void validarCampoNumericoComoString(String campo, String valorEsperado) {
        float valor = Float.parseFloat(valorEsperado);
        response.then().body(campo, equalTo(valor));
    }

    @E("o corpo da resposta deve validar com o contrato {string}")
    public void validarContratoSchema(String schemaPath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
    }

    @Dado("que existe pelo menos uma medicao registrada no sistema")
    public void garantirAoMenosUmaMedicao() {
        String payloadSetup = """
                {
                  "dispositivoId": "SETUP-INIT",
                  "setor": "DataCenter",
                  "consumoKwh": 50.0
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(payloadSetup)
                .when()
                .post("/api/v1/telemetria/consumo")
                .then()
                .statusCode(201);
    }

    @Quando("eu enviar uma requisicao GET para {string}")
    public void enviarRequisicaoGet(String endpoint) {
        response = given()
                .when()
                .get(endpoint);
    }

    @E("o corpo da resposta deve validar com o contrato de lista {string}")
    public void validarContratoListaSchema(String schemaPath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
    }
}