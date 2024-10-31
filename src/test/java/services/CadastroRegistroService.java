package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.RegistroModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroRegistroService {

    String idRegistroTrafego;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    final RegistroModel registroModel = new RegistroModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "https://api-trafego-dev-dda3bhdng8gtaqhk.eastus2-01.azurewebsites.net";

    public void setFieldsRegistro(String field, String value) {
        switch (field) {
            case "idRegistroTrafego" -> registroModel.setIdRegistroTrafego(Integer.parseInt(value));
            case "idVeiculo" -> registroModel.setIdVeiculo(Integer.parseInt(value));
            case "idMotorista" -> registroModel.setIdMotorista(Integer.parseInt(value));
            case "idRota" -> registroModel.setIdRota(Integer.parseInt(value));
            case "dataHora" -> registroModel.setDataHora(value);
            case "velocidadeVeiculo" -> registroModel.setVelocidadeVeiculo(Integer.parseInt(value));
            case "condicoesMetereologicas" -> registroModel.setCondicoesMetereologicas(value);
            case "outrosDados" -> registroModel.setOutrosDados(value);
            default -> throw new IllegalStateException("Unexpected field " + field);
        }
    }

    public void createRegistro(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(registroModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdRegistro() {
        idRegistroTrafego = String.valueOf(gson.fromJson(response.jsonPath().prettify(), RegistroModel.class).getIdRegistroTrafego());
    }

    public void deleteRegistro(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idRegistroTrafego);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void getRegistro(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idRegistroTrafego);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de registro" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-registro.json");
            default -> throw new IllegalStateException("Unexpected contract " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}
